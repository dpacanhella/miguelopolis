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
@Table(name = "cardapio_restaurante")
public class Cardapio {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CAR_CODIGO")
    private Integer id;
    
    @Column(name = "CAR_DESCRICAO")
    private String descricao;
    
    @Column(name = "CAR_IMAGEM")
    private String imagem;
    
    @ManyToOne
    @JoinColumn(name = "RES_CODIGO")
    private Restaurante restaurante;

}
