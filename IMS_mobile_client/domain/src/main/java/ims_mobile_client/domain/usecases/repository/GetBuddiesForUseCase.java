package ims_mobile_client.domain.usecases.repository;

import java.util.List;
import javax.inject.Inject;

import ims_mobile_client.domain.models.Buddy;
import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.repository.IMSRepository;
import ims_mobile_client.domain.usecases.FlowableUseCase;
import io.reactivex.Flowable;

public class GetBuddiesForUseCase extends FlowableUseCase<List<Buddy>, String> {
    private final IMSRepository repository;

    @Inject
    public GetBuddiesForUseCase(IMSRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Flowable<List<Buddy>> buildUseCaseFlowable(String usrSipUri) {
        return repository.getBuddiesFor(usrSipUri);
    }
}
