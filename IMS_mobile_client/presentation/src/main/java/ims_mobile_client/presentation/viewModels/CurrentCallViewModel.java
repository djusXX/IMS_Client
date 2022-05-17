package ims_mobile_client.presentation.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ims_mobile_client.presentation.mappers.MapperProvider;
import ims_mobile_client.presentation.models.CallView;


@HiltViewModel
public class CurrentCallViewModel extends ViewModel {
    private MutableLiveData<CallView> currentCall = new MutableLiveData<>();
    private final MapperProvider mappers;

    @Inject
    public CurrentCallViewModel(MapperProvider mappers) {
        this.mappers = mappers;
    }
}
