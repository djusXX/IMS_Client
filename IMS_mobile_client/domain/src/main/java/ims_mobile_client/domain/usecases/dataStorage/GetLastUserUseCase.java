package ims_mobile_client.domain.usecases.dataStorage;

import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.models.User;
import ims_mobile_client.domain.repository.IMSRepository;
import ims_mobile_client.domain.usecases.FlowableUseCase;
import io.reactivex.Flowable;
import javax.inject.Inject;

public class GetLastUserUseCase extends FlowableUseCase<User, Void> {
    private final IMSRepository repository;

    @Inject
    public GetLastUserUseCase(IMSRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Flowable<User> buildUseCaseFlowable(Void unused) {
        return repository.getLastUser();
    }
}
