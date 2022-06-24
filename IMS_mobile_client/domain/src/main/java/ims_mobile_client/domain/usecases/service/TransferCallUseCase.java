package ims_mobile_client.domain.usecases.service;

import javax.inject.Inject;

import ims_mobile_client.domain.models.Call;
import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.service.IMSService;
import ims_mobile_client.domain.usecases.CompletableUseCase;
import io.reactivex.Completable;

public class TransferCallUseCase extends CompletableUseCase<Call> {
    private final IMSService service;

    @Inject
    public TransferCallUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMSService service) {
        super(threadExecutor, postExecutionThread);
        this.service = service;
    }

    @Override
    protected Completable buildUseCaseObservable(Call call) {
        return service.transferCall(call);
    }
}
