//
//  UtilitarioService.swift
//  miguelopolis
//
//  Created by infra on 09/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import Alamofire
import ObjectMapper
import RSStarterKit

protocol UtilitarioRestService {
    
    func list(tipoAnuncio: String, callback: @escaping (RSServiceResponse<[Utilitarios]>) -> Void)
    
}

struct UtilitarioService: UtilitarioRestService {
    
    func list(tipoAnuncio: String, callback: @escaping (RSServiceResponse<[Utilitarios]>) -> Void) {
        var uri = RSServiceHelper.mountUrl(withEndPoint: Services.List)
        uri = String(format: uri, tipoAnuncio)
        
        RSServiceHelper.get(url: uri, callbackForObjectArray: callback)
    }

    
    // MARK: - Endpoints
    enum Services: String, RSServiceEndpoint {
        case List = "/utilitarios/%@"
    }
    
    
}
