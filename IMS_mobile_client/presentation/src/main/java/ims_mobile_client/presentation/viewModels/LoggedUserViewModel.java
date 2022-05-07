package ims_mobile_client.presentation.viewModels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ims_mobile_client.domain.models.User;
import ims_mobile_client.domain.usecases.repository.GetLastUserUseCase;
import ims_mobile_client.presentation.mappers.MapperProvider;
import ims_mobile_client.presentation.models.UserView;
import ims_mobile_client.presentation.utils.Result;
import ims_mobile_client.presentation.utils.ResultState;
import io.reactivex.subscribers.DisposableSubscriber;

public class LoggedUserViewModel extends ViewModel {
    private final GetLastUserUseCase getLastUserUseCase;
    private final MapperProvider mappers;
    private final MutableLiveData<Result<UserView>> loggedUserLiveData;

    @ViewModelInject
    public LoggedUserViewModel(GetLastUserUseCase getLastUserUseCase, MapperProvider mappers) {
        this.getLastUserUseCase = getLastUserUseCase;
        this.mappers = mappers;
        loggedUserLiveData = new MutableLiveData<>();
        getLastLoggedUser();
    }

    @Override
    protected void onCleared() {
        getLastUserUseCase.dispose();
        super.onCleared();
    }

    private void getLastLoggedUser() {
        loggedUserLiveData.postValue(new Result<>(ResultState.LOADING, null, null));
        getLastUserUseCase.execute(new LoggedUserSubscriber(), null);
    }

    private class LoggedUserSubscriber extends DisposableSubscriber<User> {

        @Override
        public void onNext(User user) {
            loggedUserLiveData.postValue(new Result<>(ResultState.SUCCESS,
                    mappers.getUserMapper().mapToView(user), null));
            getLastUserUseCase.dispose();
        }

        @Override
        public void onError(Throwable t) {
            loggedUserLiveData.postValue(new Result<>(ResultState.ERROR, null, t.getLocalizedMessage()));
        }

        @Override
        public void onComplete() {

        }
    }
}
