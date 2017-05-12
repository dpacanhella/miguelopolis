package br.com.mychesys.droid.util.task;

import br.com.mychesys.droid.business.BusinessException;

/**
 * Created by Marcelo Alves.
 */
public class AsyncTaskResult<T> {

    private T response;
    private BusinessException error;

    public AsyncTaskResult() {

    }

    public AsyncTaskResult(T response) {
        super();
        this.response = response;
    }

    public AsyncTaskResult(BusinessException error) {
        super();
        this.error = error;
    }

    public T response() {
        return response;
    }

    public void setResponse(T t) {
        response = t;
    }

    public BusinessException error() {
        return error;
    }

}
