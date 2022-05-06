package ims_mobile_client.presentation.data;

public class Resource<T> {
    private final ResourceSource source;
    private final ResourceState state;
    private final T data;
    private final String message;

    public Resource(ResourceSource source, ResourceState state, T data, String message) {
        this.source = source;
        this.state = state;
        this.data = data;
        this.message = message;
    }

    public final Resource<T> success(ResourceSource src, T data) {
        return new Resource<>(src, ResourceState.SUCCESS, data, "[ " + src.toString() + " ] Completed with SUCCESS");
    }

    public final Resource<T> error(ResourceSource src, String msg) {
        return new Resource<>(src, ResourceState.ERROR, null, "[ " + src.toString() + " ] ERROR occurred, message: " + msg);
    }

    public final Resource<T> loading(ResourceSource src) {
        return new Resource<>(src, ResourceState.LOADING, null, "[ " + src.toString() + " ] Still LOADING");
    }
}
