package ims_mobile_client.domain.usecases.repository;

import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.models.User;
import ims_mobile_client.domain.repository.IMCRepository;
import ims_mobile_client.domain.usecases.FlowableUseCase;
import io.reactivex.Flowable;
import javax.inject.Inject;

public class GetLastUserUseCase extends FlowableUseCase<User, Void> {
    private final IMCRepository repository;

    @Inject
    public GetLastUserUseCase(IMCRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Flowable<User> buildUseCaseObservable(Void unused) {
        return repository.getLastUser();
    }
}
