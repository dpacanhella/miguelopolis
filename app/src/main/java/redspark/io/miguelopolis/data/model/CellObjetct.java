package redspark.io.miguelopolis.data.model;

/**
 * Created by infra on 11/05/17.
 */

public abstract class CellObjetct<E> {
    private E object;

    public CellObjetct(){

    }

    public E getObject(){
        return this.object;
    }

    public void setObject(E object){
        this.object = object;
    }
}
