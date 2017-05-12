package br.com.mychesys.droid.util.task;

/**
 * Created by Marcelo Alves.
 */
public interface AppAsyncTask<T> {
    AsyncTaskResult<T> onStart();
    void onFinish(AsyncTaskResult<T> t);
}
