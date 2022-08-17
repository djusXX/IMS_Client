package ims_mobile_client.domain.usecases.sip;

import javax.inject.Inject;

import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.models.UserLoggedStatus;
import ims_mobile_client.domain.repository.IMSRepository;
import ims_mobile_client.domain.usecases.FlowableUseCase;
import io.reactivex.Flowable;

public class UserGetRegistrationStateUseCase extends FlowableUseCase<UserLoggedStatus, Void> {
    private final IMSRepository repository;

    @Inject
    public UserGetRegistrationStateUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMSRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Flowable<UserLoggedStatus> buildUseCaseFlowable(Void unused) {
        return repository.getRegistrationState();
    }

}
