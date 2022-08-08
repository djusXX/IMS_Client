package ims_mobile_client.presentation.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ims_mobile_client.domain.usecases.dataStorage.RemoveBuddyDataUseCase;
import ims_mobile_client.domain.usecases.dataStorage.SaveBuddyDataUseCase;
import ims_mobile_client.domain.usecases.dataStorage.GetBuddyDataListUseCase;
import ims_mobile_client.domain.usecases.sip.SubscribeBuddyPresenceUseCase;
import ims_mobile_client.domain.usecases.sip.RemoveBuddyFromBuddyListUseCase;
import ims_mobile_client.domain.usecases.sip.UnsubscribeBuddyPresenceUseCase;
import ims_mobile_client.presentation.models.BuddyInfo;

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
    private final SubscribeBuddyPresenceUseCase subscribeBuddyPresenceUseCase;
    private final UnsubscribeBuddyPresenceUseCase unsubscribeBuddyPresenceUseCase;



    private final MutableLiveData<List<BuddyInfo>> buddies = new MutableLiveData<>();


    public BuddyListViewModel(SaveBuddyDataUseCase saveBuddyDataUseCase, RemoveBuddyDataUseCase removeBuddyDataUseCase, GetBuddyDataListUseCase getBuddyDataListUseCase, SubscribeBuddyPresenceUseCase subscribeBuddyPresenceUseCase, UnsubscribeBuddyPresenceUseCase unsubscribeBuddyPresenceUseCase) {
        this.saveBuddyDataUseCase = saveBuddyDataUseCase;
        this.removeBuddyDataUseCase = removeBuddyDataUseCase;
        this.getBuddyDataListUseCase = getBuddyDataListUseCase;
        this.subscribeBuddyPresenceUseCase = subscribeBuddyPresenceUseCase;
        this.unsubscribeBuddyPresenceUseCase = unsubscribeBuddyPresenceUseCase;
        
    }
    

    public LiveData<List<BuddyInfo>> getBuddyList() {
        return buddies;
    }

    
    private void fetchSavedBuddyDataList() {}

    private void subscribePresenceStateOfBuddyList() {}

    public void addBuddyToList(String displayName, String buddySipUri) {

    }
}
