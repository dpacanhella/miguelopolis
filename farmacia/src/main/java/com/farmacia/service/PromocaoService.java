package com.farmacia.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.farmacia.controller.dto.PromocaoDTO;
import com.farmacia.domain.Promocao;

public interface PromocaoService {

//    Promocao salvar(PromocaoDTO entity, MultipartFile file) throws FileNotFoundException, IOException;

    List<Promocao> getAll(Integer id);

    Promocao update(Integer id, PromocaoDTO dto);

    void delete(Integer id);

    Promocao getById(Integer id);

    Promocao salvar(Integer farmaciaId, String nomeProduto, String descricaoProduto, String precoProduto, MultipartFile file) throws IOException;

}
