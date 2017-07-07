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
@Table(name = "lanchonetes")
public class Lanchonete {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "LAN_CODIGO")
    private Integer id;
    
    @Column(name = "LAN_NOME")
    private String nome;
    
    @Column(name = "LAN_PROPRIETARIO")
    private String nomeProprietario;
    
    @Column(name = "LAN_ENDERECO")
    private String endereco;
    
    @Column(name = "LAN_TELEFONE")
    private String telefone;
    
    @Column(name = "LAN_WHATS")
    private String whatsapp;
    
    @Column(name = "LAN_IMAGEM_ESTABELECIMENTO")
    private String imagemEstabelecimento;
    
    @Column(name = "LAN_IMAGEM_LOGO")
    private String imagemLogo;
    
    @OneToMany(mappedBy = "lanchonete", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CardapioLanchonete> cardapios = new ArrayList<CardapioLanchonete>();

}
