package com.farmacia.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "USU_CODIGO")
    private Integer id;
    
    @Column(name = "USO_LOGIN")
    private String login;
    
    @Column(name = "USO_SENHA")
    private String senha;
    
    @OneToOne
    @JoinColumn(name = "FARMA_CODIGO")
    private Farmacia farmacia;
    
    @OneToOne
    @JoinColumn(name = "LOJA_CODIGO")
    private Loja loja;

}
