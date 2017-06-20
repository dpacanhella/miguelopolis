package com.farmacia.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
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
@Table(name = "promocao_farmacia")
public class Promocao {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "PROM_CODIGO")
    private Integer id;
    
    @Column(name = "PROM_IMAGEM_PRODUTO")
    private String imagemProduto;
    
    @Column(name = "PROM_NOME_PRODUTO")
    private String nomeProduto;
    
    @Column(name = "PROM_PRECO_INICIAL")
    private String precoInicial;
    
    @Column(name = "PROM_PRECO_FINAL")
    private String precoFinal;
    
    @ManyToOne
    @JoinColumn(name = "FARMACIA_ID", foreignKey = @ForeignKey(name = "fk_farmacia"))
    private Farmacia farmacia;
}
