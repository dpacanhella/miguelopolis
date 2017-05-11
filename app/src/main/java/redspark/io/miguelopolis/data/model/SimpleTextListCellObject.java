package redspark.io.miguelopolis.data.model;

import lombok.Data;

/**
 * Created by infra on 11/05/17.
 */
@Data
public class SimpleTextListCellObject<E> extends CellObjetct<E> {

    private String textOne;
    private String textTwo;
}
