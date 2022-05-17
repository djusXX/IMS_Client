package ims_mobile_client.domain.usecases.repository;

import javax.inject.Inject;

import ims_mobile_client.domain.models.Message;
import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.repository.IMCRepository;
import ims_mobile_client.domain.usecases.CompletableUseCase;
import io.reactivex.Completable;

public class AddMessageUseCase extends CompletableUseCase<Message> {
    private final IMCRepository repository;

    @Inject
    public AddMessageUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMCRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Completable buildUseCaseObservable(Message message) {
        return repository.addMessage(message);
    }
}
