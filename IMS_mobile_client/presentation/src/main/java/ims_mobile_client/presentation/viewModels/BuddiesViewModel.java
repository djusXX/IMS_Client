package ims_mobile_client.presentation.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ims_mobile_client.presentation.models.BuddyView;

public class BuddiesViewModel extends ViewModel {

    private final MutableLiveData<List<BuddyView>> buddies = new MutableLiveData<>();

}
