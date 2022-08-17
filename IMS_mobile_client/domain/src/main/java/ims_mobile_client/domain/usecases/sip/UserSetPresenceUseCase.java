package ims_mobile_client.domain.usecases.sip;

import javax.inject.Inject;

import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.models.PresenceStatus;
import ims_mobile_client.domain.repository.IMSRepository;
import ims_mobile_client.domain.usecases.CompletableUseCase;
import io.reactivex.Completable;

public class UserSetPresenceUseCase extends CompletableUseCase<PresenceStatus> {
    private final IMSRepository repository;

    @Inject
    public UserSetPresenceUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMSRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }
    @Override
    protected Completable buildUseCaseObservable(PresenceStatus presenceStatus) {
        return repository.updateUserPresence(presenceStatus);
    }
}
