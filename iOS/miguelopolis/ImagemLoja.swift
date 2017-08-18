//
//  ImagemLoja.swift
//  miguelopolis
//
//  Created by infra on 16/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import ObjectMapper

struct ImagemLoja {
    
    var descricao: String?
    var imagem: String?
    
    
}

// MARK: - JSON Mapper
extension ImagemLoja: Mappable {
    init?(map: Map) {
        
    }
    
    mutating func mapping(map: Map) {
        descricao  <- map["descricao"]
        imagem    <- map["image64"]
        
    }
}
