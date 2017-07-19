package com.farmacia.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.farmacia.controller.dto.ImagemLojaDTO;
import com.farmacia.domain.ImagemLoja;
import com.farmacia.mapper.ImagemLojaMapper;
import com.farmacia.service.ImagemLojaService;

@RestController
@Component
@Transactional
@RequestMapping("/imagens-lojas")
public class ImagemLojaController {
    
    @Autowired
    private ImagemLojaService imgLojaService;
    
    @Autowired
    private ImagemLojaMapper imgLojaMapper;
    
    @PostMapping
    @Transactional
    public ImagemLojaDTO salvar(@RequestParam(value = "lojaId", required = true) Integer lojaId,
            @RequestParam(value = "descricaoImagem", required = true) String descricaoImagem,
            @RequestPart("file") MultipartFile file) throws FileNotFoundException, IOException {

        ImagemLoja imagemLoja = imgLojaService.salvar(lojaId, descricaoImagem, file);

        return imgLojaMapper.toDTO(imagemLoja);
    }

    @PutMapping("/{id}")
    public ImagemLojaDTO update(@PathVariable Integer id,
            @RequestParam(value = "descricaoImagem", required = true) String descricaoImagem,
            @RequestPart("file") MultipartFile file) throws FileNotFoundException, IOException {
        ImagemLoja imagemLoja = imgLojaService.update(id, descricaoImagem, file);

        return imgLojaMapper.toDTO(imagemLoja);
    }
    
    @GetMapping("/lojaId/{id}")
    public List<ImagemLojaDTO> getAll(@PathVariable Integer id) throws IOException {
        List<ImagemLoja> entity = imgLojaService.getAll(id);

        List<ImagemLojaDTO> listDTO = imgLojaMapper.toListDTO(entity);

        for (ImagemLojaDTO imagemLojaDTO : listDTO) {
            if (imagemLojaDTO.getImagemProduto() != null) {
                byte[] readFileToByteArray = FileUtils.readFileToByteArray(new File(imagemLojaDTO.getImagemProduto()));
                imagemLojaDTO.setImageByte(readFileToByteArray);
            }
        }
        
        return listDTO;
    }
    
    @GetMapping("/{id}")
    public ImagemLojaDTO getById(@PathVariable Integer id) throws IOException {
        ImagemLoja entity = imgLojaService.getById(id);
        return imgLojaMapper.toDTO(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        imgLojaService.delete(id);
    }
}
