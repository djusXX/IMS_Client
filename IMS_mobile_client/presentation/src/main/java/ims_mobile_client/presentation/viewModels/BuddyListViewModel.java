package ims_mobile_client.presentation.viewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ims_mobile_client.domain.models.Buddy;
import ims_mobile_client.domain.usecases.dataStorage.SaveBuddyDataUseCase;
import ims_mobile_client.domain.usecases.dataStorage.GetBuddyDataListUseCase;
import ims_mobile_client.domain.usecases.sip.AddNewBuddyUseCase;
import ims_mobile_client.presentation.models.BuddyInfo;
import io.reactivex.subscribers.DisposableSubscriber;

@HiltViewModel
public class BuddyListViewModel extends ViewModel {
    public static final String TAG = BuddyListViewModel.class.getName();

    // data storage usecases
    private final GetBuddyDataListUseCase getBuddyDataListUseCase;
    private final SaveBuddyDataUseCase saveBuddyDataUseCase;

    // sip usecases
    private final AddNewBuddyUseCase addNewBuddyUseCase;

    private final MutableLiveData<List<BuddyInfo>> buddyList = new MutableLiveData<>();

    @Inject
    public BuddyListViewModel(GetBuddyDataListUseCase getBuddyDataListUseCase,
                              SaveBuddyDataUseCase saveBuddyDataUseCase, AddNewBuddyUseCase addNewBuddyUseCase) {
        this.getBuddyDataListUseCase = getBuddyDataListUseCase;
        this.saveBuddyDataUseCase = saveBuddyDataUseCase;
        this.addNewBuddyUseCase = addNewBuddyUseCase;
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
        Log.d(TAG, "Calling addBuddyToList()");
        saveBuddyDataUseCase.execute(new SaveBuddyDataUseCase.Params(buddySipUri, displayName));
        addNewBuddyUseCase.execute(new AddNewBuddyUseCase.Params(buddySipUri, displayName));
        Log.d(TAG, "Buddy added to list");
    }


    public void fetchSavedBuddyList() {
        getBuddyDataListUseCase.execute(new DisposableSubscriber<List<Buddy>>() {
            @Override
            public void onNext(List<Buddy> buddies) {
                Log.d(TAG, "buddies: " + buddies);
                List<BuddyInfo> buddyInfos = new ArrayList<>();
                buddies.forEach(buddy -> {
                    buddyInfos.add(new BuddyInfo(buddy.getBuddySipUri(), buddy.getBuddyDisplayName(), buddy.getPresenceState()));
                });
                buddyList.postValue(buddyInfos);
                Log.d(TAG, "buddyList: " + buddyList.getValue());
                request(1);
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
