package redspark.io.miguelopolis.data.dao;

/**
 * Created by bruno.oliveira on 24/03/17.
 */

public interface IServiceResponse<T> {
    void onSuccess(T data);
    void onError(String error);
}
