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
    var nomeProprietario: String?
    var telefone: String?
    var endereco: String?
    var imagemLogo: String?
    var imagemEstabelecimento: String?
    var whatsapp: String?
    
}

// MARK: - JSON Mapper
extension Loja: Mappable {
    init?(map: Map) {
        
    }
    
    mutating func mapping(map: Map) {
        id    <- map["id"]
        nome  <- map["nome"]
        nomeProprietario    <- map["nomeProprietario"]
        telefone    <- map["telefone"]
        endereco    <- map["endereco"]
        imagemLogo    <- map["imagemLogo"]
        whatsapp    <- map["whatsapp"]
        imagemEstabelecimento    <- map["imagemEstabelecimento"]
    }
}

// MARK: - Equatable
extension Loja: Equatable {}
func ==(lhs: Loja, rhs: Loja) -> Bool {
    return lhs.id == rhs.id
}
