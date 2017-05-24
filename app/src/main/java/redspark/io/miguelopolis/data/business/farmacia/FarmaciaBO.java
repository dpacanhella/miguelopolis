package redspark.io.miguelopolis.data.business.farmacia;

import java.util.List;

import redspark.io.miguelopolis.data.business.BusinessException;
import redspark.io.miguelopolis.data.dao.DaoException;
import redspark.io.miguelopolis.data.dao.Farmacia.FarmaciaDao;
import redspark.io.miguelopolis.data.model.Farmacia;
import redspark.io.miguelopolis.data.model.FarmaciaDetalhes;
import retrofit2.Callback;

/**
 * Created by infra on 10/05/17.
 */

public class FarmaciaBO {

    private FarmaciaDao farmaciaDAO;

    public FarmaciaBO() {
        farmaciaDAO = new FarmaciaDao();
    }

    public List<Farmacia> getAll() throws BusinessException {
        try {
            return farmaciaDAO.getAll();
        } catch (DaoException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }
    }

    public FarmaciaDetalhes getById(int id, Callback callback) throws BusinessException {
        try {
            farmaciaDAO.getById(id, callback);
        }catch (DaoException e){
            throw new BusinessException(e.getCode(), e.getMessage());

        }

        FarmaciaDetalhes farm = new FarmaciaDetalhes();
        return farm;
    }
}
