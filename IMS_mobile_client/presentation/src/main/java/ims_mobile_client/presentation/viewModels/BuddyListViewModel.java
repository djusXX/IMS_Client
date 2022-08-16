package ims_mobile_client.presentation.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import ims_mobile_client.domain.models.Buddy;
import ims_mobile_client.domain.usecases.dataStorage.RemoveBuddyDataUseCase;
import ims_mobile_client.domain.usecases.dataStorage.SaveBuddyDataUseCase;
import ims_mobile_client.domain.usecases.dataStorage.GetBuddyDataListUseCase;
import ims_mobile_client.domain.usecases.sip.AddNewBuddyUseCase;
import ims_mobile_client.domain.usecases.sip.SubscribeBuddyPresenceUseCase;
import ims_mobile_client.domain.usecases.sip.UnsubscribeBuddyPresenceUseCase;
import ims_mobile_client.presentation.models.BuddyInfo;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * get list of Buddies from repo/dataStorage and add them to BuddyList
 *
 * for each Buddy subscribe:
 *  -   sipUri
 *  -   DisplayName
 *  -   BuddyPresence
 *
 * add Buddy to BuddyList
 * remove Buddy from BuddyList
 *
 * */
public class BuddyListViewModel extends ViewModel {
    // data storage usecases
    private final SaveBuddyDataUseCase saveBuddyDataUseCase;
    private final RemoveBuddyDataUseCase removeBuddyDataUseCase;
    private final GetBuddyDataListUseCase getBuddyDataListUseCase;

    // sip usecases
    private final AddNewBuddyUseCase addNewBuddyUseCase;
    private final SubscribeBuddyPresenceUseCase subscribeBuddyPresenceUseCase;
    private final UnsubscribeBuddyPresenceUseCase unsubscribeBuddyPresenceUseCase;


    private final MutableLiveData<List<BuddyInfo>> buddyList = new MutableLiveData<>();

    public BuddyListViewModel(SaveBuddyDataUseCase saveBuddyDataUseCase,
                              RemoveBuddyDataUseCase removeBuddyDataUseCase,
                              GetBuddyDataListUseCase getBuddyDataListUseCase,
                              AddNewBuddyUseCase addNewBuddyUseCase,
                              SubscribeBuddyPresenceUseCase subscribeBuddyPresenceUseCase,
                              UnsubscribeBuddyPresenceUseCase unsubscribeBuddyPresenceUseCase) {
        this.saveBuddyDataUseCase = saveBuddyDataUseCase;
        this.removeBuddyDataUseCase = removeBuddyDataUseCase;
        this.getBuddyDataListUseCase = getBuddyDataListUseCase;
        this.addNewBuddyUseCase = addNewBuddyUseCase;
        this.subscribeBuddyPresenceUseCase = subscribeBuddyPresenceUseCase;
        this.unsubscribeBuddyPresenceUseCase = unsubscribeBuddyPresenceUseCase;

        fetchSavedBuddyList();
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
                    buddyInfos.add(new BuddyInfo(buddy.getBuddySipUri(), buddy.getBuddyDisplayName()));
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
