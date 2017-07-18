package com.farmacia.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "imagens_lojas")
public class ImagemLoja {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "IMG_CODIGO")
    private Integer id;
    
    @Column(name = "IMG_IMAGEM")
    private String imagemProduto;
    
    @Column(name = "IMG_DESCRICAO")
    private String descricaoProduto;
    
    @ManyToOne
    @JoinColumn(name = "LOJA_CODIGO")
    private Loja loja;
}

