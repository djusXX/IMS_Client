package ims_mobile_client.domain.usecases.repository;

import javax.inject.Inject;

import ims_mobile_client.domain.models.Call;
import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.repository.IMSRepository;
import ims_mobile_client.domain.usecases.CompletableUseCase;
import io.reactivex.Completable;

public class AddCallUseCase extends CompletableUseCase<Call> {
    private final IMSRepository repository;

    @Inject
    public AddCallUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMSRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Completable buildUseCaseObservable(Call call) {
        return repository.addCall(call);
    }
}
