package br.dpacanhella.miguelopolis.data.business.farmacia;

import java.util.List;

import br.dpacanhella.miguelopolis.data.dao.DaoException;
import br.dpacanhella.miguelopolis.data.dao.Farmacia.FarmaciaDao;
import br.dpacanhella.miguelopolis.data.model.Farmacia;
import br.dpacanhella.miguelopolis.data.business.BusinessException;
import br.dpacanhella.miguelopolis.data.model.FarmaciaDetalhes;
import br.dpacanhella.miguelopolis.data.model.Restaurante;
import br.dpacanhella.miguelopolis.data.model.RestauranteDetalhes;
import br.dpacanhella.miguelopolis.data.model.Utilitario;
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

    public List<Utilitario> getAllUtilitarios() throws BusinessException {
        try {
            return farmaciaDAO.getAllUtilitarios();
        } catch (DaoException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }
    }

    public List<Restaurante> getAllRestaurantes() throws BusinessException {
        try {
            return farmaciaDAO.getAllRestaurantes();
        } catch (DaoException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }
    }

    public RestauranteDetalhes getByIdRestaurante(int id, Callback callback) throws BusinessException {
        try {
            farmaciaDAO.getByIdRestaurante(id, callback);
        }catch (DaoException e){
            throw new BusinessException(e.getCode(), e.getMessage());

        }

        RestauranteDetalhes restaurante = new RestauranteDetalhes();
        return restaurante;
    }
}
