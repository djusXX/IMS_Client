package ims_mobile_client.domain.usecases.service;

import javax.inject.Inject;

import ims_mobile_client.domain.entities.User;
import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.service.IMCSipService;
import ims_mobile_client.domain.usecases.CompletableUseCase;
import io.reactivex.Completable;

public class SetUserPresenceUseCase extends CompletableUseCase<User> {
    private final IMCSipService service;

    @Inject
    public SetUserPresenceUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMCSipService service) {
        super(threadExecutor, postExecutionThread);
        this.service = service;
    }

    @Override
    protected Completable buildUseCaseObservable(User user) {
        return service.setUserPresence(user);
    }
}
