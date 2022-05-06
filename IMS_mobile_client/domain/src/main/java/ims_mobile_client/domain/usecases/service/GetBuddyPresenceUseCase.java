package ims_mobile_client.domain.usecases.service;

import ims_mobile_client.domain.models.Buddy;
import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.service.IMCSipService;
import ims_mobile_client.domain.usecases.FlowableUseCase;
import io.reactivex.Flowable;

public class GetBuddyPresenceUseCase extends FlowableUseCase<Buddy, Buddy> {
    private final IMCSipService service;

    public GetBuddyPresenceUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMCSipService service) {
        super(threadExecutor, postExecutionThread);
        this.service = service;
    }

    @Override
    protected Flowable<Buddy> buildUseCaseObservable(Buddy buddy) {
        return service.getBuddyPresence(buddy);
    }
}
