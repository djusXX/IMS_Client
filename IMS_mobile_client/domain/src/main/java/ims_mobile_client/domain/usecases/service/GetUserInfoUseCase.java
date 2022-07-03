package ims_mobile_client.domain.usecases.service;

import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.models.User;
import ims_mobile_client.domain.sip.IMSManager;
import ims_mobile_client.domain.usecases.FlowableUseCase;
import io.reactivex.Flowable;

public class GetUserInfoUseCase extends FlowableUseCase<User, String> {
    private final IMSManager service;

    public GetUserInfoUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMSManager service) {
        super(threadExecutor, postExecutionThread);
        this.service = service;
    }

    @Override
    protected Flowable<User> buildUseCaseFlowable(String usrSipUri) {
        return service.getUserInfo(usrSipUri);
    }


}
