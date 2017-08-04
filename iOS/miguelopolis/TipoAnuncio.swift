//
//  TipoAnuncio.swift
//  miguelopolis
//
//  Created by infra on 02/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import ObjectMapper

struct TipoAnuncio {
    
    var id: Int?
    var descricao: String?
    var qtdeUtilitario: Int?
}

// MARK: - JSON Mapper
extension TipoAnuncio: Mappable {
    init?(map: Map) {
        
    }
    
    mutating func mapping(map: Map) {
        id    <- map["id"]
        descricao  <- map["descricao"]
        qtdeUtilitario    <- map["qtdeUtilitario"]
    }
}

// MARK: - Equatable
extension TipoAnuncio: Equatable {}
func ==(lhs: TipoAnuncio, rhs: TipoAnuncio) -> Bool {
    return lhs.id == rhs.id
}
