package com.farmacia.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.controller.dto.RetornoLoginDTO;
import com.farmacia.domain.Farmacia;
import com.farmacia.domain.Loja;
import com.farmacia.domain.Usuario;
import com.farmacia.repository.FarmaciaRepository;
import com.farmacia.repository.LoginRepository;
import com.farmacia.repository.LojaRepository;

@RestController
@Component
@Transactional
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    @Autowired
    private LojaRepository lojaRepository;

    @GetMapping
    public RetornoLoginDTO login(@RequestParam(required = true) String login,
            @RequestParam(required = true) String password) throws IOException {
        RetornoLoginDTO dto = new RetornoLoginDTO();
        Farmacia farmacia = null;
        Loja loja = null;

        List<Usuario> usuarios = loginRepository.findAll();

        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login) && usuario.getSenha().equals(password)) {
                if (usuario.getFarmacia() != null) {
                    farmacia = farmaciaRepository.findById(usuario.getFarmacia().getId());
                }

                if (usuario.getLoja() != null) {
                    loja = lojaRepository.findById(usuario.getLoja().getId());
                }

                if (farmacia != null) {
                    dto.setId(farmacia.getId());
                    dto.setTipo("TIPO_FARMACIA");
                } else if (loja != null) {
                    dto.setId(loja.getId());
                    dto.setTipo("TIPO_LOJA");
                }
            }

        }

        return dto;
    }

}
