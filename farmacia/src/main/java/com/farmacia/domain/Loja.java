package com.farmacia.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "lojas")
public class Loja {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "LOJA_CODIGO")
    private Integer id;
    
    @Column(name = "LOJA_NOME")
    private String nome;
    
    @Column(name = "LOJA_DESCRICAO")
    private String descricao;
    
    @Column(name = "LOJA_ENDERECO")
    private String endereco;
    
    @Column(name = "LOJA_WHATS")
    private String celular;
    
    @Column(name = "LOJA_TELEFONE")
    private String telefone;
    
    @Column(name = "LOJA_IMAGEM_ESTABELECIMENTO")
    private String imagemEstabelecimento;
    
    @Column(name = "LOJA_IMAGEM_LOGO")
    private String imagemLogo;
    
    @OneToMany(mappedBy = "loja", cascade = CascadeType.ALL)
    private List<ImagemLoja> imagensLojas = new ArrayList<ImagemLoja>();

}
