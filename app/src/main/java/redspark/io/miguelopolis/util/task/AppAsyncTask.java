package redspark.io.miguelopolis.util.task;

/**
 * Created by Ricardo Cardoso.
 */
public interface AppAsyncTask<T> {
    AsyncTaskResult<T> onStart();
    void onFinish(AsyncTaskResult<T> t);
}
