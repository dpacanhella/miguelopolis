package br.dpacanhella.miguelopolis.data.business.farmacia;

import java.util.List;

import br.dpacanhella.miguelopolis.data.dao.DaoException;
import br.dpacanhella.miguelopolis.data.dao.Farmacia.FarmaciaDao;
import br.dpacanhella.miguelopolis.data.model.Farmacia;
import br.dpacanhella.miguelopolis.data.business.BusinessException;
import br.dpacanhella.miguelopolis.data.model.FarmaciaDetalhes;
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
