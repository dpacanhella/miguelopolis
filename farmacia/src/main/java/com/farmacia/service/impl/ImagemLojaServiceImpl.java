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

import com.farmacia.domain.ImagemLoja;
import com.farmacia.domain.Loja;
import com.farmacia.repository.ImagemLojaRepository;
import com.farmacia.repository.LojaRepository;
import com.farmacia.service.ImagemLojaService;

@Service
public class ImagemLojaServiceImpl implements ImagemLojaService {

    @Autowired
    private ImagemLojaRepository imgLojaRepository;

    @Autowired
    private LojaRepository lojaRepository;

    @Override
    public ImagemLoja salvar(Integer lojaId, String descricaoImagem, MultipartFile file) throws IOException {
         String directory = "/home/farmacia/imagemLoja/";
//        String directory = "/Users/infra/Documents/imagemLoja/";
        // String directory = "/Users/diegoPacanhella/Documents/imagemLoja/";

        Loja loja = lojaRepository.findById(lojaId);

        ImagemLoja entity = new ImagemLoja();
        entity.setDescricaoProduto(descricaoImagem);
        entity.setLoja(loja);

        ImagemLoja save = imgLojaRepository.save(entity);

        if (file != null && file.getBytes().length > 0) {
            InputStream is = new ByteArrayInputStream(file.getBytes());
            String mimeType = URLConnection.guessContentTypeFromStream(is);
            if (mimeType != null) {
                mimeType = mimeType.substring(mimeType.indexOf("/") + 1, mimeType.length());
            }
            String photo = directory + "imagem_" + save.getId() + "." + mimeType;
            org.apache.commons.io.FileUtils.writeByteArrayToFile(new File(photo), file.getBytes());
            entity.setImagemProduto(photo);
            imgLojaRepository.save(entity);
        }

        return save;
    }

    @Override
    public ImagemLoja update(Integer id, String descricaoImagem, MultipartFile file) throws IOException {
         String directory = "/home/farmacia/imagemLoja/";
//        String directory = "/Users/infra/Documents/imagemLoja/";
        // String directory = "/Users/diegoPacanhella/Documents/imagemLoja/";

        ImagemLoja imgLoja = imgLojaRepository.findById(id);

        if (file != null && file.getBytes().length > 0) {
            InputStream is = new ByteArrayInputStream(file.getBytes());
            String mimeType = URLConnection.guessContentTypeFromStream(is);
            if (mimeType != null) {
                mimeType = mimeType.substring(mimeType.indexOf("/") + 1, mimeType.length());
            }
            String photo = directory + "imagem_" + imgLoja.getId() + "." + mimeType;

            File fileDelete = new File(imgLoja.getImagemProduto());
            fileDelete.delete();

            org.apache.commons.io.FileUtils.writeByteArrayToFile(new File(photo), file.getBytes());
            imgLoja.setImagemProduto(photo);
            imgLojaRepository.save(imgLoja);
        }

        imgLoja.setDescricaoProduto(descricaoImagem);

        imgLojaRepository.save(imgLoja);

        return imgLoja;
    }

    @Override
    public List<ImagemLoja> getAll(Integer id) {
        List<ImagemLoja> list = imgLojaRepository.findByLojaId(id);
        return list;
    }

    @Override
    public ImagemLoja getById(Integer id) {
        ImagemLoja imgLoja = imgLojaRepository.findById(id);
        return imgLoja;
    }

    @Override
    public void delete(Integer id) {
        ImagemLoja imagLoja = imgLojaRepository.findById(id);
        
        File fileDelete = new File(imagLoja.getImagemProduto());
        fileDelete.delete();
        
        imgLojaRepository.delete(imagLoja);
        
    }

}
