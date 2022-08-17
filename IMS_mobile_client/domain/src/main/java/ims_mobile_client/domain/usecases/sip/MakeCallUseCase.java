package ims_mobile_client.domain.usecases.sip;

import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.repository.IMSRepository;
import ims_mobile_client.domain.usecases.CompletableUseCase;
import io.reactivex.Completable;

public class MakeCallUseCase extends CompletableUseCase<MakeCallUseCase.Params> {
    private final IMSRepository repository;
    protected MakeCallUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMSRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Completable buildUseCaseObservable(MakeCallUseCase.Params params) {
        return repository.makeCall(params.buddySipUri, params.isVideo);
    }

    public static class Params {
        public String buddySipUri;
        public boolean isVideo;

        public Params(String buddySipUri, boolean isVideo) {
            this.buddySipUri = buddySipUri;
            this.isVideo = isVideo;
        }
    }
}
