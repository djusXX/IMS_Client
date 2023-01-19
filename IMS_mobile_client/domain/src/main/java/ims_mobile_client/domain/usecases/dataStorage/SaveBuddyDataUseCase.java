package ims_mobile_client.domain.usecases.dataStorage;

import javax.inject.Inject;

import ims_mobile_client.domain.models.Buddy;
import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.repository.IMSRepository;
import ims_mobile_client.domain.usecases.CompletableUseCase;
import io.reactivex.Completable;

public class SaveBuddyDataUseCase extends CompletableUseCase<SaveBuddyDataUseCase.Params> {
    private final IMSRepository repository;

    @Inject
    protected SaveBuddyDataUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMSRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Completable buildUseCaseObservable(Params p) {
        return repository.saveBuddy(p.buddySipUri, p.buddyDisplayName);
    }

    public static class Params {
        public String buddySipUri;
        public String buddyDisplayName;

        public Params(String buddySipUri, String displayName) {
            this.buddySipUri = buddySipUri;
            this.buddyDisplayName = displayName;
        }
    }
}
