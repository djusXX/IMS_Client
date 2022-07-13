package ims_mobile_client.presentation.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ims_mobile_client.presentation.models.BuddyInfo;

public class BuddiesViewModel extends ViewModel {

    private final MutableLiveData<List<BuddyInfo>> buddies = new MutableLiveData<>();

}
