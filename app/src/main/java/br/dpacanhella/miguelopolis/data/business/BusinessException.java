package br.dpacanhella.miguelopolis.data.business;

/**
 * Created by ricardocardoso on 11/05/17.
 */

public class BusinessException extends Exception{

    private int code;

    public BusinessException() {
        super();
    }

    public BusinessException(Throwable t) {super(t);}

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
