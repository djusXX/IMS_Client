package ims_mobile_client.presentation.viewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import ims_mobile_client.domain.models.User;
import ims_mobile_client.domain.usecases.repository.GetLastUserUseCase;
import ims_mobile_client.domain.usecases.service.LogInUserUseCase;
import ims_mobile_client.presentation.mappers.MapperProvider;
import ims_mobile_client.presentation.models.UserView;
import ims_mobile_client.presentation.utils.Result;
import ims_mobile_client.presentation.utils.ResultState;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DisposableSubscriber;

@HiltViewModel
public class CurrentUserViewModel extends ViewModel {
    private final GetLastUserUseCase getLastUserUseCase;
    private final LogInUserUseCase logInUserUseCase;
    private final MapperProvider mappers;
    private final MutableLiveData<Result<UserView>> currentUserLiveData;

    @Inject
    public CurrentUserViewModel(GetLastUserUseCase getLastUserUseCase, LogInUserUseCase logInUserUseCase, MapperProvider mappers) {
        this.getLastUserUseCase = getLastUserUseCase;
        this.logInUserUseCase = logInUserUseCase;
        this.mappers = mappers;
        currentUserLiveData = new MutableLiveData<>();
        getLastLoggedUser();
    }

    @Override
    protected void onCleared() {
        getLastUserUseCase.dispose();
        logInUserUseCase.unsubscribe();
        super.onCleared();
    }

    public LiveData<Result<UserView>> getLastUser() {
        return currentUserLiveData;
    }

    private void getLastLoggedUser() {
        getLastUserUseCase.execute(new DisposableSubscriber<User>() {
            @Override
            protected void onStart() {
                currentUserLiveData.postValue(new Result<>(ResultState.LOADING, null, null));
            }

            @Override
            public void onNext(User user) {
                logInUser(user);
                getLastUserUseCase.dispose();
            }

            @Override
            public void onError(Throwable t) {
                currentUserLiveData.postValue(new Result<>(ResultState.ERROR, null, t.getLocalizedMessage()));
                getLastUserUseCase.dispose();
            }

            @Override
            public void onComplete() {

            }
        }, null);
    }



    public void logInUserView(UserView userView) {
        User user = new User(0, userView.getName(), userView.getDisplayName(), userView.getRealm(), userView.getPcscf(), 0);
        logInUser(user);
    }

    private void logInUser(User user) {
        logInUserUseCase.execute(user).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("logInUserUseCase.execute", "onStart()");
                logInUserUseCase.setSubscribe(d);
                currentUserLiveData.postValue(new Result<>(ResultState.LOADING, null, null));
            }

            @Override
            public void onComplete() {
                currentUserLiveData.postValue(new Result<>(ResultState.SUCCESS,
                        mappers.getUserMapper().mapToView(user), null));
                Log.d("logInUserUseCase.execute", "onSuccess()");
                logInUserUseCase.unsubscribe();
            }

            @Override
            public void onError(Throwable e) {
                currentUserLiveData.postValue(new Result<>(ResultState.ERROR, null, e.getLocalizedMessage()));
                Log.d("logInUserUseCase.execute", "onError()");
                logInUserUseCase.unsubscribe();
            }
        });

    }
}
