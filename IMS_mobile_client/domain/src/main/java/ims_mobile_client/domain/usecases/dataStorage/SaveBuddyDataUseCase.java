package ims_mobile_client.domain.usecases.dataStorage;

import javax.inject.Inject;

import ims_mobile_client.domain.models.Buddy;
import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.repository.IMSRepository;
import ims_mobile_client.domain.usecases.CompletableUseCase;
import io.reactivex.Completable;

public class SaveBuddyDataUseCase extends CompletableUseCase<Buddy> {
    private final IMSRepository repository;

    @Inject
    protected SaveBuddyDataUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMSRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Completable buildUseCaseObservable(Buddy buddy) {
        return repository.saveBuddy(buddy);
    }
}
