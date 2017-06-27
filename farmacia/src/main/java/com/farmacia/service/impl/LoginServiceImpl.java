package com.farmacia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmacia.domain.Farmacia;
import com.farmacia.domain.Usuario;
import com.farmacia.exception.FarmaciaException;
import com.farmacia.repository.FarmaciaRepository;
import com.farmacia.repository.LoginRepository;
import com.farmacia.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
    
    @Autowired
    private LoginRepository loginRepository;
    
    @Autowired
    private FarmaciaRepository farmaciaRepository;

    @Override
    public Farmacia logar(String login, String password) {
        Farmacia farmacia = null;
        
        List<Usuario> usuarios = loginRepository.findAll();
        
        for (Usuario usuario : usuarios) {
            if(usuario.getLogin().equals(login) && usuario.getSenha().equals(password)){
                farmacia = farmaciaRepository.findById(usuario.getFarmacia().getId());
            }
        }
        
        if(farmacia == null){
            throw FarmaciaException.LOGIN_NOT_FOUND;
        }
        
        return farmacia;
    }

}
