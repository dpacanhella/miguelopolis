package redspark.io.miguelopolis.data.business.farmacia;

import java.io.IOException;
import java.util.List;

import redspark.io.miguelopolis.data.dao.FarmaciaDao;
import redspark.io.miguelopolis.data.model.Farmacia;

/**
 * Created by infra on 10/05/17.
 */

public class FarmaciaBO {

    private FarmaciaDao farmaciaDAO;

    public FarmaciaBO() {
        farmaciaDAO = new FarmaciaDao();
    }

    public List<Farmacia> getAll(){
        return farmaciaDAO.getAll();
    }
}
