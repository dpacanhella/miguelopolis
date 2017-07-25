//
//  Promocao.swift
//  miguelopolis
//
//  Created by Marcos Vinícius Fernandes on 7/5/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import ObjectMapper

struct Promocao {
    
    var id: Int?
    var imagemProduto: String?
    var nomeProduto: String?
    var precoInicial: String?
    var precoFinal: String?
    var image64: String?
    
}

// MARK: - JSON Mapper
extension Promocao: Mappable {
    init?(map: Map) {
        
    }
    
    mutating func mapping(map: Map) {
        id    <- map["id"]
        imagemProduto  <- map["imagemProduto"]
        nomeProduto    <- map["nomeProduto"]
        precoInicial    <- map["precoInicial"]
        precoFinal    <- map["precoFinal"]
        image64    <- map["image64"]
    }
}

// MARK: - Equatable
extension Promocao: Equatable {}
func ==(lhs: Promocao, rhs: Promocao) -> Bool {
    return lhs.id == rhs.id
}
