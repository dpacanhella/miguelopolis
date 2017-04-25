package com.farmacia.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "farmacia_plantao")
public class Farmacia {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "FARM_CODIGO")
    private Integer id;
    
    @Column(name = "FARM_RAZAO")
    private String razao;
    
    @Column(name = "FARM_NOME_PROPRIETARIO")
    private String nomeProprietario;
    
    @Column(name = "FARM_TELEFONE")
    private String telefone;
    
    @Column(name = "FARM_ENDERECO")
    private String endereco;
    
    @Column(name = "FARM_LINK_IMAGEM")
    private String imagem;
    
    @Column(name = "FARM_PLANTAO")
    private char plantao;
}
