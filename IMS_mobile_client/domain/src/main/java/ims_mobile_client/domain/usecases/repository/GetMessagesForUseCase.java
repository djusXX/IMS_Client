package ims_mobile_client.domain.usecases.repository;

import java.util.List;

import javax.inject.Inject;

import ims_mobile_client.domain.entities.Message;
import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.repository.ImsMobileClientRepository;
import ims_mobile_client.domain.usecases.FlowableUseCase;
import ims_mobile_client.domain.usecases.Pair;
import io.reactivex.Flowable;

public class GetMessagesForUseCase extends FlowableUseCase<List<Message>, Pair<String,String>> {
    private final ImsMobileClientRepository repository;

    @Inject
    public GetMessagesForUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, ImsMobileClientRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Flowable<List<Message>> buildUseCaseObservable(Pair<String, String> uriPair) {
        return repository.getMessagesFor(uriPair.first, uriPair.second);
    }
}
