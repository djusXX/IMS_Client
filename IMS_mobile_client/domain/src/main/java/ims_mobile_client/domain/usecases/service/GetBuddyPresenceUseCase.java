package ims_mobile_client.domain.usecases.service;

import ims_mobile_client.domain.models.Buddy;
import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.service.IMSService;
import ims_mobile_client.domain.usecases.FlowableUseCase;
import io.reactivex.Flowable;

public class GetBuddyPresenceUseCase extends FlowableUseCase<Buddy, Buddy> {
    private final IMSService service;

    public GetBuddyPresenceUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMSService service) {
        super(threadExecutor, postExecutionThread);
        this.service = service;
    }

    @Override
    protected Flowable<Buddy> buildUseCaseFlowable(Buddy buddy) {
        return service.getBuddyPresence(buddy);
    }
}
