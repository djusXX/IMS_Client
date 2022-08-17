package ims_mobile_client.presentation.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ims_mobile_client.domain.models.Buddy;
import ims_mobile_client.domain.usecases.dataStorage.RemoveBuddyDataUseCase;
import ims_mobile_client.domain.usecases.dataStorage.SaveBuddyDataUseCase;
import ims_mobile_client.domain.usecases.dataStorage.GetBuddyDataListUseCase;
import ims_mobile_client.domain.usecases.sip.AddNewBuddyUseCase;
import ims_mobile_client.domain.usecases.sip.SubscribeBuddyPresenceUseCase;
import ims_mobile_client.domain.usecases.sip.UnsubscribeBuddyPresenceUseCase;
import ims_mobile_client.presentation.models.BuddyInfo;
import io.reactivex.subscribers.DisposableSubscriber;

@HiltViewModel
public class BuddyListViewModel extends ViewModel {
    // data storage usecases
    private final GetBuddyDataListUseCase getBuddyDataListUseCase;

    // sip usecases
    private final AddNewBuddyUseCase addNewBuddyUseCase;

    private final MutableLiveData<List<BuddyInfo>> buddyList = new MutableLiveData<>();

    @Inject
    public BuddyListViewModel(GetBuddyDataListUseCase getBuddyDataListUseCase,
                              AddNewBuddyUseCase addNewBuddyUseCase) {
        this.getBuddyDataListUseCase = getBuddyDataListUseCase;
        this.addNewBuddyUseCase = addNewBuddyUseCase;

        fetchSavedBuddyList();
    }

    @Override
    protected void onCleared() {
        getBuddyDataListUseCase.dispose();
        super.onCleared();
    }

    public LiveData<List<BuddyInfo>> getBuddyList() {
        return buddyList;
    }

    public void addBuddyToList(String displayName, String buddySipUri) {
        addNewBuddyUseCase.execute(new AddNewBuddyUseCase.Params(buddySipUri, displayName));
    }


    private void fetchSavedBuddyList() {
        getBuddyDataListUseCase.execute(new DisposableSubscriber<List<Buddy>>() {
            @Override
            public void onNext(List<Buddy> buddies) {
                List<BuddyInfo> buddyInfos = new ArrayList<>();
                buddies.forEach(buddy -> {
                    buddyInfos.add(new BuddyInfo(buddy.getBuddySipUri(), buddy.getBuddyDisplayName(), buddy.getPresenceState()));
                });
                buddyList.postValue(buddyInfos);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        }, null);
    }




}
