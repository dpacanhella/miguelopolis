//
//  LojaService.swift
//  miguelopolis
//
//  Created by infra on 04/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import Alamofire
import ObjectMapper
import RSStarterKit

protocol LojaRestService {
    
    func list(callback: @escaping (RSServiceResponse<[Loja]>) -> Void)
    
}

struct LojaService: LojaRestService {
    
    // MARK: - Endpoints
    enum Services: String, RSServiceEndpoint {
        case List = "lojas"
    }
    
    func list(callback: @escaping (RSServiceResponse<[Loja]>) -> Void) {
        
        let uri = RSServiceHelper.mountUrl(withEndPoint: Services.List)
        
        RSServiceHelper.get(url: uri, callbackForObjectArray: callback)
        
    }
    
}
