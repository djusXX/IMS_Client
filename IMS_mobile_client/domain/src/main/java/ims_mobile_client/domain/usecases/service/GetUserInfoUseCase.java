package ims_mobile_client.domain.usecases.service;

import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.models.User;
import ims_mobile_client.domain.service.IMCSipService;
import ims_mobile_client.domain.usecases.FlowableUseCase;
import io.reactivex.Flowable;

public class GetUserInfoUseCase extends FlowableUseCase<User, String> {
    private final IMCSipService service;

    public GetUserInfoUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, IMCSipService service) {
        super(threadExecutor, postExecutionThread);
        this.service = service;
    }

    @Override
    protected Flowable<User> buildUseCaseObservable(String usrSipUri) {
        return service.getUserInfo(usrSipUri);
    }


}
