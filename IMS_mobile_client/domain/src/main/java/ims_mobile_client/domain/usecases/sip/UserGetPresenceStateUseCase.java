package ims_mobile_client.domain.usecases.sip;

import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.models.PresenceState;
import ims_mobile_client.domain.repository.IMSRepository;
import ims_mobile_client.domain.usecases.FlowableUseCase;
import io.reactivex.Flowable;

public class UserGetPresenceStateUseCase extends FlowableUseCase<PresenceState, String> {
    private final IMSRepository repository;

    public UserGetPresenceStateUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMSRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }
    @Override
    protected Flowable<PresenceState> buildUseCaseFlowable(String usrSipUri) {
        return repository.getUserPresenceState(usrSipUri);
    }
}
