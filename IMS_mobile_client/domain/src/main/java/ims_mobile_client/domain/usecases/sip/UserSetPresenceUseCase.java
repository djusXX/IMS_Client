package ims_mobile_client.domain.usecases.sip;

import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.models.PresenceState;
import ims_mobile_client.domain.sip.IMSManager;
import ims_mobile_client.domain.usecases.CompletableUseCase;
import io.reactivex.Completable;

public class UserSetPresenceUseCase extends CompletableUseCase<PresenceState> {
    private final IMSManager imsManager;

    public UserSetPresenceUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMSManager imsManager) {
        super(threadExecutor, postExecutionThread);
        this.imsManager = imsManager;
    }
    @Override
    protected Completable buildUseCaseObservable(PresenceState presenceState) {
        return imsManager.updateUserPresence(presenceState);
    }
}
