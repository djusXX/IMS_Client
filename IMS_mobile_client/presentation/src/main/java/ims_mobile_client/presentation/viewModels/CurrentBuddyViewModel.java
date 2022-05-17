package ims_mobile_client.presentation.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ims_mobile_client.presentation.mappers.MapperProvider;
import ims_mobile_client.presentation.models.BuddyView;

@HiltViewModel
public class CurrentBuddyViewModel extends ViewModel {
    private MutableLiveData<BuddyView> currentBuddy = new MutableLiveData<>();
    private final MapperProvider mappers;

    @Inject
    public CurrentBuddyViewModel(MapperProvider mappers) {
        this.mappers = mappers;
    }

}
