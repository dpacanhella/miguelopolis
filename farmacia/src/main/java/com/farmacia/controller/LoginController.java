package com.farmacia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.controller.dto.FarmaciaDTO;
import com.farmacia.domain.Farmacia;
import com.farmacia.mapper.FarmaciaMapper;
import com.farmacia.service.LoginService;

@RestController
@Component
@Transactional
@RequestMapping("login")
public class LoginController {
    
    @Autowired
    private LoginService loginService;
    
    @Autowired
    private FarmaciaMapper farmaciaMapper;
    
    @GetMapping
    public FarmaciaDTO login(@RequestParam(required = true) String login, @RequestParam(required = true) String password) {
        Farmacia farmacia = loginService.logar(login, password);
        
        return farmaciaMapper.toDTO(farmacia);
    }

}
