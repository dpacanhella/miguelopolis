//
//  Cardapios.swift
//  miguelopolis
//
//  Created by infra on 15/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import ObjectMapper

struct Cardapios {
    
    var descricao: String?
    var imagem: String?

    
}

// MARK: - JSON Mapper
extension Cardapios: Mappable {
    init?(map: Map) {
        
    }
    
    mutating func mapping(map: Map) {
        descricao  <- map["descricao"]
        imagem    <- map["imagem"]

    }
}
