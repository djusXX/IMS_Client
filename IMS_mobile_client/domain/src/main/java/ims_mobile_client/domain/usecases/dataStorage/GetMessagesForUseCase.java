package ims_mobile_client.domain.usecases.dataStorage;

import java.util.List;

import javax.inject.Inject;

import ims_mobile_client.domain.models.Message;
import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.repository.IMSRepository;
import ims_mobile_client.domain.usecases.FlowableUseCase;
import io.reactivex.Flowable;

public class GetMessagesForUseCase extends FlowableUseCase<List<Message>, String> {
    private final IMSRepository repository;

    @Inject
    public GetMessagesForUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMSRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Flowable<List<Message>> buildUseCaseFlowable(String buddySipUri) {
        return repository.getMessagesFor(buddySipUri);
    }

}
