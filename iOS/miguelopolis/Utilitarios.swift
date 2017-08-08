//
//  Utilitarios.swift
//  miguelopolis
//
//  Created by infra on 07/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import ObjectMapper

struct Utilitarios {
    
    var id: Int?
    var nome: String?
    var descricao: String?
    var telefone: String?
    var endereco: String?
    var imagem: String?
    var tipoAnuncio: String?
    var celular: String?
    
}

// MARK: - JSON Mapper
extension Utilitarios: Mappable {
    init?(map: Map) {
        
    }
    
    mutating func mapping(map: Map) {
        id    <- map["id"]
        nome  <- map["nome"]
        descricao    <- map["descricao"]
        telefone    <- map["telefone"]
        endereco    <- map["endereco"]
        imagem    <- map["imagem"]
        celular    <- map["celular"]
        tipoAnuncio    <- map["tipoAnuncio"]
    }
}

// MARK: - Equatable
extension Utilitarios: Equatable {}
func ==(lhs: Utilitarios, rhs: Utilitarios) -> Bool {
    return lhs.id == rhs.id
}
