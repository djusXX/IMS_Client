package ims_mobile_client.domain.usecases.service;

import ims_mobile_client.domain.entities.Buddy;
import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.service.pjsua2ImsService;
import ims_mobile_client.domain.usecases.FlowableUseCase;
import io.reactivex.Flowable;

public class GetBuddyPresenceUseCase extends FlowableUseCase<Buddy, Buddy> {
    private final pjsua2ImsService service;

    public GetBuddyPresenceUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, pjsua2ImsService service) {
        super(threadExecutor, postExecutionThread);
        this.service = service;
    }

    @Override
    protected Flowable<Buddy> buildUseCaseObservable(Buddy buddy) {
        return service.getBuddyPresence(buddy);
    }
}
