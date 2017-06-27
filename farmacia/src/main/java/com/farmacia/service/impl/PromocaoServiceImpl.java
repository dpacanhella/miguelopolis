package com.farmacia.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.farmacia.controller.dto.PromocaoDTO;
import com.farmacia.domain.Promocao;
import com.farmacia.mapper.FarmaciaMapper;
import com.farmacia.repository.PromocaoRepository;
import com.farmacia.service.PromocaoService;

import ch.qos.logback.core.util.FileUtil;

@Service
public class PromocaoServiceImpl implements PromocaoService {
    
    @Autowired
    private PromocaoRepository promocaoRepository;
    
    @Autowired
    private FarmaciaMapper farmaciaMapper;

    @Override
    public Promocao salvar(PromocaoDTO dto, MultipartFile file) throws FileNotFoundException, IOException {
//        String directory = "/home/farmacia/promocoes/";
        String directory = "/Users/infra/Documents/promocoes/";     
        
        Promocao entity = new Promocao();
        entity.setImagemProduto(dto.getImagemProduto());
        entity.setNomeProduto(dto.getNomeProduto());
        entity.setPrecoInicial(dto.getPrecoInicial());
        entity.setPrecoFinal(dto.getPrecoFinal());
        entity.setFarmacia(farmaciaMapper.toEntity(dto.getFarmaciaDTO()));
        
        Promocao save = promocaoRepository.save(entity);
        
        if(file != null && file.getBytes().length > 0){
            InputStream is = new ByteArrayInputStream(file.getBytes());
            String mimeType = URLConnection.guessContentTypeFromStream(is);
            if(mimeType != null){
                mimeType = mimeType.substring(mimeType.indexOf("/") + 1, mimeType.length());
            }
            String photo = directory + "promocao_" + save.getId() + "." + mimeType;
            org.apache.commons.io.FileUtils.writeByteArrayToFile(new File(photo), file.getBytes());
            entity.setImage64(photo);
            promocaoRepository.save(entity);
        }
        
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
