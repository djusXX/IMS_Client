package ims_mobile_client.domain.usecases.service;

import javax.inject.Inject;

import ims_mobile_client.domain.entities.Call;
import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.service.IMCSipService;
import ims_mobile_client.domain.usecases.CompletableUseCase;
import ims_mobile_client.domain.usecases.Pair;
import io.reactivex.Completable;

public class SendDTMFUseCase extends CompletableUseCase<Pair<Call,String>> {
    private final IMCSipService service;

    @Inject
    public SendDTMFUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMCSipService service) {
        super(threadExecutor, postExecutionThread);
        this.service = service;
    }

    @Override
    protected Completable buildUseCaseObservable(Pair<Call,String> pair) {
        return service.sendDTMF(pair.first, pair.second);
    }
}
