package com.farmacia.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.farmacia.domain.ImagemLoja;

public interface ImagemLojaService {

    ImagemLoja salvar(Integer lojaId, String descricaoImagem, MultipartFile file) throws IOException;

    ImagemLoja update(Integer id, String descricaoImagem, MultipartFile file) throws IOException;

    List<ImagemLoja> getAll(Integer id);

    ImagemLoja getById(Integer id);

    void delete(Integer id);

}
