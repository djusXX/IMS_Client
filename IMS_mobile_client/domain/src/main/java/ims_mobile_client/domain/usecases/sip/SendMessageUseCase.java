package ims_mobile_client.domain.usecases.sip;

import javax.inject.Inject;

import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.repository.IMSRepository;
import ims_mobile_client.domain.usecases.CompletableUseCase;
import io.reactivex.Completable;

public class SendMessageUseCase extends CompletableUseCase<SendMessageUseCase.Params> {
    private final IMSRepository repository;

    @Inject
    protected SendMessageUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMSRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Completable buildUseCaseObservable(Params params) {
        return repository.sendMessage(params.buddySipUri, params.content);
    }


    public static class Params {
        public String buddySipUri;
        public String content;

        public Params(String buddySipUri, String content) {
            this.buddySipUri = buddySipUri;
            this.content = content;
        }
    }
}
