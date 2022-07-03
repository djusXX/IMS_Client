package ims_mobile_client.domain.usecases.sip;

import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.models.RegistrationState;
import ims_mobile_client.domain.models.User;
import ims_mobile_client.domain.sip.IMSManager;
import ims_mobile_client.domain.usecases.FlowableUseCase;
import io.reactivex.Flowable;

public class UserGetRegistrationStateUseCase extends FlowableUseCase<RegistrationState, String> {
    private final IMSManager imsManager;

    public UserGetRegistrationStateUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMSManager imsManager) {
        super(threadExecutor, postExecutionThread);
        this.imsManager = imsManager;
    }

    @Override
    protected Flowable<RegistrationState> buildUseCaseFlowable(String usrSipUri) {
        return imsManager.getRegistrationState(usrSipUri);
    }

}
