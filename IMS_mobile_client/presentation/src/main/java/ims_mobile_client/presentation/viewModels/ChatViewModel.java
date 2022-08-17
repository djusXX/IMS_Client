package ims_mobile_client.presentation.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ims_mobile_client.domain.models.Message;
import ims_mobile_client.domain.usecases.dataStorage.GetMessagesForUseCase;
import ims_mobile_client.domain.usecases.sip.MakeCallUseCase;
import ims_mobile_client.domain.usecases.sip.SendMessageUseCase;
import io.reactivex.subscribers.DisposableSubscriber;

@HiltViewModel
public class ChatViewModel extends ViewModel {
    private final GetMessagesForUseCase getMessagesForUseCase;
    private final SendMessageUseCase sendMessageUseCase;
    private final MakeCallUseCase makeCallUseCase;


    private String buddySipUri;
    private final MutableLiveData<List<Message>> chatMessages = new MutableLiveData<>();

    @Inject
    public ChatViewModel(GetMessagesForUseCase getMessagesForUseCase, SendMessageUseCase sendMessageUseCase, MakeCallUseCase makeCallUseCase) {
        this.getMessagesForUseCase = getMessagesForUseCase;
        this.sendMessageUseCase = sendMessageUseCase;
        this.makeCallUseCase = makeCallUseCase;
    }

    public void start(String buddySipUri) {
        this.buddySipUri = buddySipUri;
        getMessagesForUseCase.execute(new DisposableSubscriber<List<Message>>() {
            @Override
            public void onNext(List<Message> messages) {
                chatMessages.postValue(messages);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        }, buddySipUri);
    }

    public LiveData<List<Message>> getMessages() {
        return chatMessages;
    }

    public void sendMessage(String content) {
        sendMessageUseCase.execute(new SendMessageUseCase.Params(buddySipUri, content));
    }

    public void makeCall(boolean isVideo) {
        makeCallUseCase.execute(new MakeCallUseCase.Params(buddySipUri, isVideo));
    }
}
