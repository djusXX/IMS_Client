package ims_mobile_client.domain.usecases.service;

import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.models.User;
import ims_mobile_client.domain.service.IMSService;
import ims_mobile_client.domain.usecases.FlowableUseCase;
import io.reactivex.Flowable;

public class GetRegistrationStateUseCase extends FlowableUseCase<User, User> {
    private final IMSService imsService;

    public GetRegistrationStateUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMSService imsService) {
        super(threadExecutor, postExecutionThread);
        this.imsService = imsService;
    }

    @Override
    protected Flowable<User> buildUseCaseFlowable(User user) {
        return imsService.getUserData(user.getId());
    }

}
