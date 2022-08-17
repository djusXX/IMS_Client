package ims_mobile_client.domain.usecases.sip;

import javax.inject.Inject;

import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.models.Call;
import ims_mobile_client.domain.repository.IMSRepository;
import ims_mobile_client.domain.usecases.FlowableUseCase;
import io.reactivex.Flowable;

public class GetCurrentCallUseCase extends FlowableUseCase<Call, Void> {
    private final IMSRepository repository;

    @Inject
    public GetCurrentCallUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMSRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Flowable<Call> buildUseCaseFlowable(Void unused) {
        return repository.getCurrentCall();
    }
}
