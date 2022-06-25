package ims_mobile_client.presentation.utils;

public class Result<T> {
    private final RequestState state;
    private final T data;
    private final String message;

    public Result(RequestState state, T data, String message) {
        this.state = state;
        this.data = data;
        this.message = message;
    }

    public RequestState getState() {
        return state;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public final class Error extends Result<T> {
        public Error(String message) {
            super(RequestState.ERROR, null, message);
        }
    }

    public final class Loading extends Result<T> {
        public Loading() {
            super(RequestState.LOADING, null, null);
        }
    }

    public final class Success extends Result<T> {
        public Success(T data) {
            super(RequestState.SUCCESS, data, null);
        }
    }

}
