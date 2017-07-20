package br.dpacanhella.miguelopolis.data.business.farmacia;

import java.util.ArrayList;
import java.util.List;

import br.dpacanhella.miguelopolis.data.dao.DaoException;
import br.dpacanhella.miguelopolis.data.dao.Farmacia.FarmaciaDao;
import br.dpacanhella.miguelopolis.data.model.Farmacia;
import br.dpacanhella.miguelopolis.data.business.BusinessException;
import br.dpacanhella.miguelopolis.data.model.FarmaciaDetalhes;
import br.dpacanhella.miguelopolis.data.model.Lanchonete;
import br.dpacanhella.miguelopolis.data.model.LanchoneteDetalhes;
import br.dpacanhella.miguelopolis.data.model.Loja;
import br.dpacanhella.miguelopolis.data.model.LojaDetalhes;
import br.dpacanhella.miguelopolis.data.model.Restaurante;
import br.dpacanhella.miguelopolis.data.model.RestauranteDetalhes;
import br.dpacanhella.miguelopolis.data.model.TipoAnuncio;
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

    public List<Utilitario> getAllUtilitarios(String anuncio, Callback callback) throws BusinessException {
        try {
            farmaciaDAO.getAllUtilitarios(anuncio, callback);
        } catch (DaoException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }

        List<Utilitario> utilitarios = new ArrayList<Utilitario>();
        return utilitarios;
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

    public List<Lanchonete> getAllLanchonetes() throws BusinessException {
        try {
            return farmaciaDAO.getAllLanchonetes();
        } catch (DaoException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }
    }

    public LanchoneteDetalhes getByIdLanchonetes(int id, Callback callback) throws BusinessException {
        try {
            farmaciaDAO.getByIdLanchonetes(id, callback);
        }catch (DaoException e){
            throw new BusinessException(e.getCode(), e.getMessage());

        }

        LanchoneteDetalhes lanchonete = new LanchoneteDetalhes();
        return lanchonete;
    }

    public List<TipoAnuncio> getAllAnuncios() throws BusinessException {
        try {
            return farmaciaDAO.getAllAnuncios();
        } catch (DaoException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }
    }


    public List<Loja> getAllLojas() throws BusinessException {
        try {
            return farmaciaDAO.getAllLojas();
        } catch (DaoException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        }
    }

    public LojaDetalhes getByIdLojas(int id, Callback callback) throws BusinessException {
        try {
            farmaciaDAO.getByIdLLojas(id, callback);
        }catch (DaoException e){
            throw new BusinessException(e.getCode(), e.getMessage());

        }

        LojaDetalhes loja = new LojaDetalhes();
        return loja;
    }
}
