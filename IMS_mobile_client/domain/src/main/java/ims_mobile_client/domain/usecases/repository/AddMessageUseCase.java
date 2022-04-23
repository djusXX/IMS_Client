package ims_mobile_client.domain.usecases.repository;

import javax.inject.Inject;

import ims_mobile_client.domain.entities.Message;
import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.repository.ImsMobileClientRepository;
import ims_mobile_client.domain.usecases.CompletableUseCase;
import io.reactivex.Completable;

public class AddMessageUseCase extends CompletableUseCase<Message> {
    private final ImsMobileClientRepository repository;

    @Inject
    public AddMessageUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, ImsMobileClientRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Completable buildUseCaseObservable(Message message) {
        return repository.addMessage(message);
    }
}
