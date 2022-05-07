package ims_mobile_client.presentation.utils;

public class Result<T> {
    private final ResultState state;
    private final T data;
    private final String message;

    public Result(ResultState state, T data, String message) {
        this.state = state;
        this.data = data;
        this.message = message;
    }

    public final class Error extends Result<T> {
        public Error(String message) {
            super(ResultState.ERROR, null, message);
        }
    }

    public final class Loading extends Result<T> {
        public Loading() {
            super(ResultState.LOADING, null, null);
        }
    }

    public final class Success extends Result<T> {
        public Success(T data) {
            super(ResultState.SUCCESS, data, null);
        }
    }

}
