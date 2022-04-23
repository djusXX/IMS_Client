package ims_mobile_client.domain.usecases.repository;

import javax.inject.Inject;

import ims_mobile_client.domain.entities.Call;
import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.repository.ImsMobileClientRepository;
import ims_mobile_client.domain.usecases.CompletableUseCase;
import io.reactivex.Completable;

public class AddCallUseCase extends CompletableUseCase<Call> {
    private final ImsMobileClientRepository repository;

    @Inject
    public AddCallUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, ImsMobileClientRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Completable buildUseCaseObservable(Call call) {
        return repository.addCall(call);
    }
}
