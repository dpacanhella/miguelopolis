//
//  Restaurantes.swift
//  miguelopolis
//
//  Created by infra on 03/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import ObjectMapper

struct Restaurante {
    
    var id: Int?
    var nome: String?
    var nomeProprietario: String?
    var telefone: String?
    var endereco: String?
    var imagemLogo: String?
    var whatsapp: String?
    var descricao: String?
    var descricao2: String?
    var descricao3: String?
    var descricao4: String?
    var descricao5: String?
    var imagemEstabelecimento: String?
    var imagem1: String?
    var imagem2: String?
    
}

// MARK: - JSON Mapper
extension Restaurante: Mappable {
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
        descricao    <- map["descricao"]
        descricao2    <- map["descricao2"]
        descricao3    <- map["descricao3"]
        descricao4    <- map["descricao4"]
        descricao5    <- map["descricao5"]
        imagemEstabelecimento    <- map["imagemEstabelecimento"]
        imagem1    <- map["imagem1"]
        imagem2    <- map["imagem2"]
    }
}

// MARK: - Equatable
extension Restaurante: Equatable {}
func ==(lhs: Restaurante, rhs: Restaurante) -> Bool {
    return lhs.id == rhs.id
}
