package ims_mobile_client.domain.usecases.service;

import javax.inject.Inject;

import ims_mobile_client.domain.entities.Call;
import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.service.pjsua2ImsService;
import ims_mobile_client.domain.usecases.CompletableUseCase;
import io.reactivex.Completable;

public class ToggleMuteUseCase extends CompletableUseCase<Call> {
    private final pjsua2ImsService service;

    @Inject
    public ToggleMuteUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, pjsua2ImsService service) {
        super(threadExecutor, postExecutionThread);
        this.service = service;
    }

    @Override
    protected Completable buildUseCaseObservable(Call call) {
        return service.toggleMute(call);
    }
}
