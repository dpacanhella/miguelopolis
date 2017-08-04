//
//  RestauranteService.swift
//  miguelopolis
//
//  Created by infra on 03/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import Alamofire
import ObjectMapper
import RSStarterKit

protocol RestauranteRestService {
    
    func list(callback: @escaping (RSServiceResponse<[Restaurante]>) -> Void)
    
}

struct RestauranteService: RestauranteRestService {
    
    // MARK: - Endpoints
    enum Services: String, RSServiceEndpoint {
        case List = "restaurantes"
    }
    
    func list(callback: @escaping (RSServiceResponse<[Restaurante]>) -> Void) {
        
        let uri = RSServiceHelper.mountUrl(withEndPoint: Services.List)
        
        RSServiceHelper.get(url: uri, callbackForObjectArray: callback)
        
    }
    
}
