package ims_mobile_client.domain.usecases.sip;

import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.models.PresenceState;
import ims_mobile_client.domain.repository.IMSRepository;
import ims_mobile_client.domain.usecases.CompletableUseCase;
import io.reactivex.Completable;

public class UserSetPresenceUseCase extends CompletableUseCase<PresenceState> {
    private final IMSRepository repository;

    public UserSetPresenceUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMSRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }
    @Override
    protected Completable buildUseCaseObservable(PresenceState presenceState) {
        return repository.updateUserPresence(presenceState);
    }
}
