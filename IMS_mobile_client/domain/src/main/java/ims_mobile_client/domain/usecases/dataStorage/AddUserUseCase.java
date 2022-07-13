package ims_mobile_client.domain.usecases.dataStorage;



import javax.inject.Inject;

import ims_mobile_client.domain.models.User;
import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.repository.IMSRepository;
import ims_mobile_client.domain.usecases.CompletableUseCase;
import io.reactivex.Completable;

public class AddUserUseCase extends CompletableUseCase<User> {
    private final IMSRepository repository;

    @Inject
    public AddUserUseCase(IMSRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Completable buildUseCaseObservable(User user) {
        return repository.addUser(user);
    }
}
