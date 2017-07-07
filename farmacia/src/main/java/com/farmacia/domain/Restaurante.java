package com.farmacia.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "restaurantes")
public class Restaurante {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "RES_CODIGO")
    private Integer id;
    
    @Column(name = "RES_NOME")
    private String nome;
    
    @Column(name = "RES_PROPRIETARIO")
    private String nomeProprietario;
    
    @Column(name = "RES_ENDERECO")
    private String endereco;
    
    @Column(name = "RES_TELEFONE")
    private String telefone;
    
    @Column(name = "RES_WHATSAPP")
    private String whatsapp;
    
    @Column(name = "RES_DESCRICAO")
    private String descricao;
    
    @Column(name = "RES_DESCRICAO2")
    private String descricao2;
    
    @Column(name = "RES_DESCRICAO3")
    private String descricao3;
    
    @Column(name = "RES_DESCRICAO4")
    private String descricao4;
    
    @Column(name = "RES_DESCRICAO5")
    private String descricao5;
    
    @Column(name = "RES_IMAGEM_LOGO")
    private String imagemLogo;
    
    @Column(name = "RES_IMAGEM_ESTABELECIMENTO")
    private String imagemEstabelecimento;
    
    @Column(name = "RES_IMAGEM1")
    private String imagem1;
    
    @Column(name = "RES_IMAGEM2")
    private String imagem2;
    
    @OneToMany(mappedBy = "restaurante", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CardapioRestaurante> cardapios = new ArrayList<CardapioRestaurante>();

}
