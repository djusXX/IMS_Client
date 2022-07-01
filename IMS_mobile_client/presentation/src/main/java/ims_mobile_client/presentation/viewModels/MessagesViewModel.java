package ims_mobile_client.presentation.viewModels;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ims_mobile_client.presentation.models.MessageView;

public class MessagesViewModel {

    private final MutableLiveData<List<MessageView>> messages = new MutableLiveData<>();
}
