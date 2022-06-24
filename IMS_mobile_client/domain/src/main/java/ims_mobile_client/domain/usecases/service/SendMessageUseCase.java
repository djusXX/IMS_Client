package ims_mobile_client.domain.usecases.service;

import javax.inject.Inject;
import ims_mobile_client.domain.models.Message;
import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.service.IMSService;
import ims_mobile_client.domain.usecases.CompletableUseCase;
import io.reactivex.Completable;

public class SendMessageUseCase extends CompletableUseCase<Message> {
    private final IMSService service;

    @Inject
    public SendMessageUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMSService service) {
        super(threadExecutor, postExecutionThread);
        this.service = service;
    }

    @Override
    protected Completable buildUseCaseObservable(Message message) {
        return service.sendMessage(message);
    }
}
