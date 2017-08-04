//
//  Lanchonete.swift
//  miguelopolis
//
//  Created by infra on 04/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import ObjectMapper

struct Loja {
    
    var id: Int?
    var nome: String?
    var descricao: String?
    var telefone: String?
    var endereco: String?
    var imagemLogo: String?
    var imagemEstabelecimento: String?
    var celular: String?
    
}

// MARK: - JSON Mapper
extension Loja: Mappable {
    init?(map: Map) {
        
    }
    
    mutating func mapping(map: Map) {
        id    <- map["id"]
        nome  <- map["nome"]
        descricao    <- map["descricao"]
        telefone    <- map["telefone"]
        endereco    <- map["endereco"]
        imagemLogo    <- map["imagemLogo"]
        celular    <- map["celular"]
        imagemEstabelecimento    <- map["imagemEstabelecimento"]
    }
}

// MARK: - Equatable
extension Loja: Equatable {}
func ==(lhs: Loja, rhs: Loja) -> Bool {
    return lhs.id == rhs.id
}
