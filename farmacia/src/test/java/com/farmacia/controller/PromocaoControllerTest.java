package com.farmacia.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.farmacia.FarmaciaApplicationTests;
import com.farmacia.controller.dto.FarmaciaDTO;
import com.farmacia.controller.dto.PromocaoDTO;
import com.farmacia.domain.Promocao;
import com.farmacia.mapper.FarmaciaMapper;
import com.farmacia.repository.FarmaciaRepository;
import com.farmacia.repository.PromocaoRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Ignore
public class PromocaoControllerTest extends FarmaciaApplicationTests {
    
    @Autowired
    private TestRestTemplate testRestTemplate;
    
    @Autowired
    private FarmaciaRepository farmaciaRepository;
    
    @Autowired
    private FarmaciaMapper farmaciaMapper;
    
    @Autowired
    private PromocaoRepository promocoRepository;
   
    
    @Test
    public void shouldSavePromocao(){
        FarmaciaDTO farmaciaDTO = new FarmaciaDTO();
        farmaciaDTO.setId(1);
        farmaciaDTO.setPlantao(false);
        farmaciaDTO.setEndereco("Endereço");
        farmaciaDTO.setNomeProprietario("Nome Proprietario");
        farmaciaDTO.setRazao("Razao");
        farmaciaDTO.setTelefone("Telefone");
        farmaciaDTO.setWhatsApp("WhatsApp");
        farmaciaDTO.setImagem("imagem...");
        
        farmaciaRepository.save(farmaciaMapper.toEntity(farmaciaDTO));
        
        PromocaoDTO dto = new PromocaoDTO();
        dto.setFarmaciaDTO(farmaciaDTO);
        dto.setImagemProduto("imagem produto");
        dto.setNomeProduto("Creatine");
        dto.setPrecoInicial("Ganho de massa muscular");
        dto.setPrecoFinal("R$ 10,00");
        
        ResponseEntity<PromocaoDTO> responseEntity = testRestTemplate.postForEntity("/promocoes", dto, PromocaoDTO.class);
        
        PromocaoDTO promocao = responseEntity.getBody();
        
        assertNotNull(promocao.getId());
        assertEquals(dto.getNomeProduto(), promocao.getNomeProduto());
        assertEquals(dto.getPrecoInicial(), promocao.getPrecoInicial());
        assertEquals(dto.getPrecoFinal(), promocao.getPrecoFinal());
        assertEquals(dto.getImagemProduto(), promocao.getImagemProduto());
    }

    @Test
    public void shouldUpdatePromocao(){
        FarmaciaDTO farmaciaDTO = new FarmaciaDTO();
        farmaciaDTO.setId(1);
        farmaciaDTO.setPlantao(false);
        farmaciaDTO.setEndereco("Endereço");
        farmaciaDTO.setNomeProprietario("Nome Proprietario");
        farmaciaDTO.setRazao("Razao");
        farmaciaDTO.setTelefone("Telefone");
        farmaciaDTO.setWhatsApp("WhatsApp");
        farmaciaDTO.setImagem("imagem...");
        
        farmaciaRepository.save(farmaciaMapper.toEntity(farmaciaDTO));
        
        PromocaoDTO dto = new PromocaoDTO();
        dto.setFarmaciaDTO(farmaciaDTO);
        dto.setImagemProduto("imagem produto");
        dto.setNomeProduto("Creatine");
        dto.setPrecoInicial("Ganho de massa muscular");
        dto.setPrecoFinal("R$ 10,00");
        
        ResponseEntity<PromocaoDTO> responseEntity = testRestTemplate.postForEntity("/promocoes", dto, PromocaoDTO.class);
        
        PromocaoDTO promocao = responseEntity.getBody();
        
        
        PromocaoDTO updateDTO = new PromocaoDTO();
        updateDTO.setImagemProduto("imagem produto2");
        updateDTO.setNomeProduto("Creatine2");
        updateDTO.setPrecoInicial("Ganho de massa muscular2");
        updateDTO.setPrecoFinal("R$ 100,00");
        
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<PromocaoDTO> requestEntity = new HttpEntity<PromocaoDTO>(updateDTO, headers);
        
        HttpEntity<PromocaoDTO> responseEntity2 = testRestTemplate.exchange("/promocoes/" + promocao.getId(), HttpMethod.PUT, requestEntity, PromocaoDTO.class);
        
        PromocaoDTO promocaoAtualizada = responseEntity2.getBody();
        
        assertNotNull(promocaoAtualizada.getId());
        assertEquals(updateDTO.getNomeProduto(), promocaoAtualizada.getNomeProduto());
        assertEquals(updateDTO.getPrecoInicial(), promocaoAtualizada.getPrecoInicial());
        assertEquals(updateDTO.getPrecoFinal(), promocaoAtualizada.getPrecoFinal());
        assertEquals(updateDTO.getImagemProduto(), promocaoAtualizada.getImagemProduto());

    }
    
    @Test
    public void shouldDeletePromocao(){
        FarmaciaDTO farmaciaDTO = new FarmaciaDTO();
        farmaciaDTO.setId(1);
        farmaciaDTO.setPlantao(false);
        farmaciaDTO.setEndereco("Endereço");
        farmaciaDTO.setNomeProprietario("Nome Proprietario");
        farmaciaDTO.setRazao("Razao");
        farmaciaDTO.setTelefone("Telefone");
        farmaciaDTO.setWhatsApp("WhatsApp");
        farmaciaDTO.setImagem("imagem...");
        
        farmaciaRepository.save(farmaciaMapper.toEntity(farmaciaDTO));
        
        PromocaoDTO dto = new PromocaoDTO();
        dto.setFarmaciaDTO(farmaciaDTO);
        dto.setImagemProduto("imagem produto");
        dto.setNomeProduto("Creatine");
        dto.setPrecoInicial("Ganho de massa muscular");
        dto.setPrecoFinal("R$ 10,00");
        
        ResponseEntity<PromocaoDTO> responseEntity = testRestTemplate.postForEntity("/promocoes", dto, PromocaoDTO.class);
        
        PromocaoDTO promocao = responseEntity.getBody();
        
        testRestTemplate.exchange("/empresas/" + promocao.getId(), HttpMethod.DELETE, null, Void.class);

        Promocao findById = promocoRepository.findById(promocao.getId());
        
        assertNotNull(findById.getId());

    }
  
}
