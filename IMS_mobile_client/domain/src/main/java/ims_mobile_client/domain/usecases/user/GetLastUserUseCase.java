package ims_mobile_client.domain.usecases.user;

import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.repository.ImsMobileClientRepository;
import ims_mobile_client.domain.usecases.FlowableUseCase;
import io.reactivex.Flowable;
import javax.inject.Inject;

public class GetLastUserUseCase extends FlowableUseCase {
    private final ImsMobileClientRepository repository;

    @Inject
    public GetLastUserUseCase(ImsMobileClientRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Flowable buildUseCaseObservable(Object o) {
        return repository.getLastUser();
    }
}
