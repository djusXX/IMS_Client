package ims_mobile_client.domain.usecases.service;

import javax.inject.Inject;

import ims_mobile_client.domain.models.User;
import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.sip.IMSManager;
import ims_mobile_client.domain.usecases.CompletableUseCase;
import io.reactivex.Completable;

public class LogOutUserUseCase extends CompletableUseCase<User> {
    private final IMSManager service;

    @Inject
    public LogOutUserUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMSManager service) {
        super(threadExecutor, postExecutionThread);
        this.service = service;
    }

    @Override
    protected Completable buildUseCaseObservable(User user) {
        return service.logOut(user);
    }
}
