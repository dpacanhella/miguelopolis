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
@Table(name = "utilitarios")
public class Utilitario {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "UTI_CODIGO")
    private Integer id;
    
    @Column(name = "UTI_NOME")
    private String nome;
    
    @Column(name = "UTI_DESCRICAO")
    private String descricao;
    
    @Column(name = "UTI_ENDERECO")
    private String endereco;
    
    @Column(name = "UTI_TELEFONE")
    private String telefone;
    
    @Column(name = "UTI_CELULAR")
    private String celular;
    
    @Column(name = "UTI_LINK_IMAGEM")
    private String imagem;
    
    @Column(name = "UTI_TIPO_ANUNCIO")
    private String tipoAnuncio;

}
