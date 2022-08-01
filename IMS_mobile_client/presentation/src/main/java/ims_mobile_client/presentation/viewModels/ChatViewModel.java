package ims_mobile_client.presentation.viewModels;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ims_mobile_client.presentation.models.MessageInfo;

/***
 * usrSipUri
 * buddySipUri
 *
 * startVoiceCall   // with this buddy
 * startVideoCall   // with this buddy
 *
 * load(History)Messages
 * sendMessage
 *
 */
public class ChatViewModel {

    private final MutableLiveData<List<MessageInfo>> messages = new MutableLiveData<>();
}
