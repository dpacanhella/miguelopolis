package com.farmacia.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.farmacia.domain.Promocao;

public interface PromocaoService {

    List<Promocao> getAll(Integer id);

    void delete(Integer id);

    Promocao getById(Integer id);

    Promocao salvar(Integer farmaciaId, String nomeProduto, String descricaoProduto, String precoProduto, MultipartFile file) throws IOException;

    Promocao update(Integer id, String nomeProduto, String descricaoProduto, String precoProduto, MultipartFile file) throws IOException;

}
