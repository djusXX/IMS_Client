package ims_mobile_client.domain.usecases.sip;

import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.models.PresenceState;
import ims_mobile_client.domain.sip.IMSManager;
import ims_mobile_client.domain.usecases.FlowableUseCase;
import io.reactivex.Flowable;

public class UserGetPresenceStateUseCase extends FlowableUseCase<PresenceState, String> {
    private final IMSManager imsManager;

    public UserGetPresenceStateUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMSManager imsManager) {
        super(threadExecutor, postExecutionThread);
        this.imsManager = imsManager;
    }
    @Override
    protected Flowable<PresenceState> buildUseCaseFlowable(String usrSipUri) {
        return imsManager.getUserPresenceState(usrSipUri);
    }
}
