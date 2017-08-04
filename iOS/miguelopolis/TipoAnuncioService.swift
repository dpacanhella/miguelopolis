//
//  TipoAnuncioService.swift
//  miguelopolis
//
//  Created by infra on 02/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import Alamofire
import ObjectMapper
import RSStarterKit

protocol TipoAnuncioRestService {
    
    func list(callback: @escaping (RSServiceResponse<[TipoAnuncio]>) -> Void)
    
}

struct TipoAnuncioService: TipoAnuncioRestService {
    
    // MARK: - Endpoints
    enum Services: String, RSServiceEndpoint {
        case List = "utilitarios/anuncios"
    }
    
    func list(callback: @escaping (RSServiceResponse<[TipoAnuncio]>) -> Void) {
        
        let uri = RSServiceHelper.mountUrl(withEndPoint: Services.List)
        
        RSServiceHelper.get(url: uri, callbackForObjectArray: callback)
        
    }
    
}
