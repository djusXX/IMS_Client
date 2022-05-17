package ims_mobile_client.domain.usecases.repository;

import java.util.List;

import javax.inject.Inject;

import ims_mobile_client.domain.models.Call;
import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.repository.IMCRepository;
import ims_mobile_client.domain.usecases.FlowableUseCase;
import io.reactivex.Flowable;

public class GetCallsForUseCase extends FlowableUseCase<List<Call>, GetCallsForUseCase.Params> {
    private final IMCRepository repository;

    @Inject
    public GetCallsForUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMCRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Flowable<List<Call>> buildUseCaseObservable(Params params) {
        return repository.getCallsFor(params.usrSipUri, params.buddySipUri);
    }

    public static class Params {
        String usrSipUri;
        String buddySipUri;
        public Params(String usrSipUri, String buddySipUri) {
            this.usrSipUri = usrSipUri;
            this.buddySipUri = buddySipUri;
        }
    }
}
