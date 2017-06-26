package com.farmacia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmacia.controller.dto.PromocaoDTO;
import com.farmacia.domain.Promocao;
import com.farmacia.repository.PromocaoRepository;
import com.farmacia.service.PromocaoService;

@Service
public class PromocaoServiceImpl implements PromocaoService {
    
    @Autowired
    private PromocaoRepository promocaoRepository;

    @Override
    public Promocao salvar(Promocao entity) {
        Promocao save = promocaoRepository.save(entity);
        return save;
    }

    @Override
    public List<Promocao> getAll(Integer id) {
        List<Promocao> list = promocaoRepository.findByFarmaciaId(id);
        return list;
    }

    @Override
    public Promocao update(Integer id, PromocaoDTO dto) {
        Promocao promocao = promocaoRepository.findById(id);
        
        promocao.setImagemProduto(dto.getImagemProduto());
        promocao.setNomeProduto(dto.getNomeProduto());
        promocao.setPrecoInicial(dto.getPrecoInicial());
        promocao.setPrecoFinal(dto.getPrecoFinal());
        
        promocaoRepository.save(promocao);
        
        return promocao;
    }

    @Override
    public void delete(Integer id) {
        Promocao promocao = promocaoRepository.findById(id);
        promocaoRepository.delete(promocao);
    }

    @Override
    public Promocao getById(Integer id) {
        Promocao promocao = promocaoRepository.findById(id);
        return promocao;
    }

}
