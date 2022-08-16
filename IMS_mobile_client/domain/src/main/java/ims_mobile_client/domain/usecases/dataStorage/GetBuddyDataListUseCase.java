package ims_mobile_client.domain.usecases.dataStorage;

import java.util.List;
import javax.inject.Inject;

import ims_mobile_client.domain.models.Buddy;
import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.repository.IMSRepository;
import ims_mobile_client.domain.usecases.FlowableUseCase;
import io.reactivex.Flowable;

public class GetBuddyDataListUseCase extends FlowableUseCase<List<Buddy>, Void> {
    private final IMSRepository repository;

    @Inject
    public GetBuddyDataListUseCase(IMSRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Flowable<List<Buddy>> buildUseCaseFlowable(Void unused) {
        return repository.getBuddiesFor();
    }
}
