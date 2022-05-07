package ims_mobile_client.presentation.data;

public class Result<T> {
    private final ResultState state;
    private final T data;
    private final String message;

    public Result(ResultState state, T data, String message) {
        this.state = state;
        this.data = data;
        this.message = message;
    }

    public final Result<T> success(T data) {
        return new Result<>(ResultState.SUCCESS, data, null);
    }

    public final Result<T> error(String msg) {
        return new Result<>(ResultState.ERROR, null, "ERROR occurred, message: " + msg);
    }

    public final Result<T> loading() {
        return new Result<>(ResultState.LOADING, null, null);
    }
}
