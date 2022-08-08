package ims_mobile_client.presentation.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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
public class ChatViewModel extends ViewModel {

    private final MutableLiveData<List<MessageInfo>> messages = new MutableLiveData<>();

    /**
     * returns all messages for logged user
     * */
    public LiveData<List<MessageInfo>> getMessages() {
        return messages;
    }

    /**
     * returns all messages between logged user and buddy
     * */
    public LiveData<List<MessageInfo>> getMessages(String buddySipUri) {
        return messages;
    }

    /**
     * returns last <number> of messages between logged user and buddy
     * */
    public LiveData<List<MessageInfo>> getMessages(String buddySipUri, int number) {
        return messages;
    }

    public void sendMessage(String buddySipUri, String content, long time) {

    }
}
