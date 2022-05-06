package ims_mobile_client.domain.usecases.repository;



import javax.inject.Inject;

import ims_mobile_client.domain.entities.User;
import ims_mobile_client.domain.executors.PostExecutionThread;
import ims_mobile_client.domain.executors.ThreadExecutor;
import ims_mobile_client.domain.repository.IMCRepository;
import ims_mobile_client.domain.usecases.CompletableUseCase;
import io.reactivex.Completable;

public class AddUserUseCase extends CompletableUseCase<User> {
    private final IMCRepository repository;

    @Inject
    public AddUserUseCase(IMCRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Completable buildUseCaseObservable(User user) {
        return repository.addUser(user);
    }
}
