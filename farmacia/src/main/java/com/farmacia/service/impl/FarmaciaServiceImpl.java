package com.farmacia.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmacia.domain.Farmacia;
import com.farmacia.repository.FarmaciaRepository;
import com.farmacia.service.FarmaciaService;

@Service
public class FarmaciaServiceImpl implements FarmaciaService {

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    @Override
    public List<Farmacia> updateAll() {
        List<Farmacia> farmacias = farmaciaRepository.findAll();

        Farmacia proxima = null;

        Farmacia farmaciaPlantao = farmaciaRepository.findByPlantao(true);

        farmaciaPlantao.setPlantao(false);

        proxima = farmaciaRepository.findById(farmaciaPlantao.getId() + 1);

        if (proxima != null) {
            proxima.setPlantao(true);
            farmaciaRepository.save(proxima);
        } else if (farmaciaPlantao.getId() + 1 > 10) {

            for (Farmacia farmacia : farmacias) {
                farmacia.setPlantao(false);
                farmaciaRepository.save(farmacia);
            }

            farmaciaPlantao = farmaciaRepository.findById(1);

            farmaciaPlantao.setPlantao(true);
            farmaciaRepository.save(farmaciaPlantao);
        }

        List<Farmacia> novasFarmacias = farmaciaRepository.findAll();

        return novasFarmacias;
    }

    @Override
    public List<Farmacia> getAll() {
        List<Farmacia> listaVazia = new ArrayList<Farmacia>();
        
        Farmacia  plantao = farmaciaRepository.findByPlantao(true);
        List<Farmacia> todasFarmácias = farmaciaRepository.findByPlantaoFalse(false);
        
        Collections.shuffle(todasFarmácias);
        
        listaVazia.add(plantao);
        listaVazia.addAll(todasFarmácias);
        todasFarmácias.add(plantao);
        
        return listaVazia;
    }

    @Override
    public Farmacia getById(Integer id) {
        return farmaciaRepository.findById(id);
    }
}
