package ims_mobile_client.presentation.viewModels;

import androidx.lifecycle.MutableLiveData;

import ims_mobile_client.presentation.models.ActiveCall;
import ims_mobile_client.presentation.models.HistoryCall;

public class CallsViewModel {

    private final MutableLiveData<ActiveCall> activeCalls = new MutableLiveData<>();
    private final MutableLiveData<HistoryCall> historyCalls = new MutableLiveData<>();
}
