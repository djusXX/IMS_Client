package ims_mobile_client.domain.usecases.repository;

import java.util.List;

import javax.inject.Inject;

import ims_mobile_client.domain.models.Message;
import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.repository.IMSRepository;
import ims_mobile_client.domain.usecases.FlowableUseCase;
import io.reactivex.Flowable;

public class GetMessagesForUseCase extends FlowableUseCase<List<Message>, GetMessagesForUseCase.Params> {
    private final IMSRepository repository;

    @Inject
    public GetMessagesForUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMSRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Flowable<List<Message>> buildUseCaseFlowable(Params params) {
        return repository.getMessagesFor(params.usrSipUri, params.buddySipUri);
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
