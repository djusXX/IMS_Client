package ims_mobile_client.domain.usecases.dataStorage;

import javax.inject.Inject;

import ims_mobile_client.domain.models.Message;
import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.repository.IMSRepository;
import ims_mobile_client.domain.usecases.CompletableUseCase;
import io.reactivex.Completable;

public class AddMessageUseCase extends CompletableUseCase<Message> {
    private final IMSRepository repository;

    @Inject
    public AddMessageUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMSRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Completable buildUseCaseObservable(Message message) {
        return repository.saveMessage(message);
    }
}
