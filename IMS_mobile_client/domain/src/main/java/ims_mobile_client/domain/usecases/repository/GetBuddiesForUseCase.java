package ims_mobile_client.domain.usecases.repository;

import java.util.List;
import javax.inject.Inject;

import ims_mobile_client.domain.entities.Buddy;
import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.repository.ImsMobileClientRepository;
import ims_mobile_client.domain.usecases.FlowableUseCase;
import io.reactivex.Flowable;

public class GetBuddiesForUseCase extends FlowableUseCase<List<Buddy>, String> {
    private final ImsMobileClientRepository repository;

    @Inject
    public GetBuddiesForUseCase(ImsMobileClientRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Flowable<List<Buddy>> buildUseCaseObservable(String usrSipUri) {
        return repository.getBuddiesFor(usrSipUri);
    }
}
