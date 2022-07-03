package ims_mobile_client.domain.usecases.sip;

import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.models.User;
import ims_mobile_client.domain.sip.IMSManager;
import ims_mobile_client.domain.usecases.CompletableUseCase;
import io.reactivex.Completable;

public class UserRegisterUseCase extends CompletableUseCase<User> {
    private final IMSManager imsManager;

    public UserRegisterUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMSManager imsManager) {
        super(threadExecutor, postExecutionThread);
        this.imsManager = imsManager;
    }

    @Override
    protected Completable buildUseCaseObservable(User u) {
        return imsManager.registerUser(u);
    }
}
