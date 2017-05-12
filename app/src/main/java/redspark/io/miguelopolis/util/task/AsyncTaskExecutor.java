package redspark.io.miguelopolis.util.task;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

/**
 * Created by Ricardo Cardoso.
 */
public class AsyncTaskExecutor {

    public void startExecutor(@NonNull AppAsyncTask appAsyncTask) {
        new BackgroundAsyncTask<>(appAsyncTask).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private class BackgroundAsyncTask<T> extends AsyncTask<Void, Void, AsyncTaskResult<T>> {

        private AppAsyncTask<T> mAppAsyncTask;

        public BackgroundAsyncTask(AppAsyncTask appAsyncTask) {
            mAppAsyncTask = appAsyncTask;
        }

        @Override
        protected AsyncTaskResult<T> doInBackground(Void... params) {
            return mAppAsyncTask.onStart();
        }

        @Override
        protected void onPostExecute(AsyncTaskResult<T> t) {
            mAppAsyncTask.onFinish(t);
        }
    }

}
