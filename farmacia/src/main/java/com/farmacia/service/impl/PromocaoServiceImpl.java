package com.farmacia.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.farmacia.domain.Farmacia;
import com.farmacia.domain.Promocao;
import com.farmacia.repository.FarmaciaRepository;
import com.farmacia.repository.PromocaoRepository;
import com.farmacia.service.PromocaoService;

@Service
public class PromocaoServiceImpl implements PromocaoService {
    
    @Autowired
    private PromocaoRepository promocaoRepository;
    
    @Autowired
    private FarmaciaRepository farmaciaRepository; 

    @Override
    public Promocao salvar(Integer farmaciaId, String nomeProduto, String descricaoProduto, String precoProduto, MultipartFile file) throws IOException {
        String directory = "/home/farmacia/promocoes/";
//        String directory = "/Users/infra/Documents/promocoes/";    
//        String directory = "/Users/diegoPacanhella/Documents/promocoes/";
        
        Farmacia farmacia = farmaciaRepository.findById(farmaciaId);
        
        Promocao entity = new Promocao();
        entity.setNomeProduto(nomeProduto);
        entity.setPrecoInicial(descricaoProduto);
        entity.setPrecoFinal(precoProduto);
        entity.setFarmacia(farmacia);
        
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
    public Promocao update(Integer id, String nomeProduto, String descricaoProduto, String precoProduto, MultipartFile file) throws IOException {
      String directory = "/home/farmacia/promocoes/";
//      String directory = "/Users/infra/Documents/promocoes/";    
//      String directory = "/Users/diegoPacanhella/Documents/promocoes/";
      
        Promocao promocao = promocaoRepository.findById(id);
        
        if(file != null && file.getBytes().length > 0){
            InputStream is = new ByteArrayInputStream(file.getBytes());
            String mimeType = URLConnection.guessContentTypeFromStream(is);
            if(mimeType != null){
                mimeType = mimeType.substring(mimeType.indexOf("/") + 1, mimeType.length());
            }
            String photo = directory + "promocao_" + promocao.getId() + "." + mimeType;
            
            File fileDelete = new File(promocao.getImage64());
            fileDelete.delete();
            
            org.apache.commons.io.FileUtils.writeByteArrayToFile(new File(photo), file.getBytes());
            promocao.setImage64(photo);
            promocaoRepository.save(promocao);
        }
        
        
        promocao.setNomeProduto(nomeProduto);
        promocao.setPrecoInicial(descricaoProduto);
        promocao.setPrecoFinal(precoProduto);
        
        promocaoRepository.save(promocao);
        
        return promocao;
    }

    @Override
    public void delete(Integer id) {
        Promocao promocao = promocaoRepository.findById(id);
        
        File fileDelete = new File(promocao.getImage64());
        fileDelete.delete();
        
        promocaoRepository.delete(promocao);
    }

    @Override
    public Promocao getById(Integer id) {
        Promocao promocao = promocaoRepository.findById(id);
        return promocao;
    }

}
