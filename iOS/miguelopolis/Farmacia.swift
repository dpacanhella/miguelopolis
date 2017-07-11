//
//  Farmacia.swift
//  miguelopolis
//
//  Created by Marcos Vinícius Fernandes on 7/5/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import ObjectMapper

struct Farmacia {
    
    var id: Int?
    var razao: String?
    var nomeProprietario: String?
    var telefone: String?
    var endereco: String?
    var imagem: String?
    var plantao: Bool?
    var whatsApp: String?
    
}

// MARK: - JSON Mapper
extension Farmacia: Mappable {
    init?(map: Map) {
        
    }
    
    mutating func mapping(map: Map) {
        id    <- map["id"]
        razao  <- map["razao"]
        nomeProprietario    <- map["nomeProprietario"]
        telefone    <- map["telefone"]
        endereco    <- map["endereco"]
        imagem    <- map["imagem"]
        plantao    <- map["plantao"]
        whatsApp    <- map["whatsApp"]
    }
}

// MARK: - Equatable
extension Farmacia: Equatable {}
func ==(lhs: Farmacia, rhs: Farmacia) -> Bool {
    return lhs.id == rhs.id
}
