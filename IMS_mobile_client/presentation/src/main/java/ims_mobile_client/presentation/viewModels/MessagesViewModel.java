package ims_mobile_client.presentation.viewModels;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ims_mobile_client.presentation.models.MessageInfo;

public class MessagesViewModel {

    private final MutableLiveData<List<MessageInfo>> messages = new MutableLiveData<>();
}
