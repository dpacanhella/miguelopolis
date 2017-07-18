package com.farmacia.controller;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
@Transactional
@RequestMapping("/imagens-lojas")
public class ImagemLojaController {

}
