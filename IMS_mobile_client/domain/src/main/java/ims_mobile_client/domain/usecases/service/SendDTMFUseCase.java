package ims_mobile_client.domain.usecases.service;

import javax.inject.Inject;

import ims_mobile_client.domain.models.Call;
import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.service.IMCSipService;
import ims_mobile_client.domain.usecases.CompletableUseCase;
import io.reactivex.Completable;

public class SendDTMFUseCase extends CompletableUseCase<SendDTMFUseCase.Params> {
    private final IMCSipService service;

    @Inject
    public SendDTMFUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMCSipService service) {
        super(threadExecutor, postExecutionThread);
        this.service = service;
    }

    @Override
    protected Completable buildUseCaseObservable(Params params) {
        return service.sendDTMF(params.call, params.sequence);
    }

    public static class Params {
        Call call;
        String sequence;
        public Params(Call call, String sequence) {
            this.call = call;
            this.sequence = sequence;
        }
    }
}
