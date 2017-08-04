//
//  LanchoneteService.swift
//  miguelopolis
//
//  Created by infra on 04/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import Alamofire
import ObjectMapper
import RSStarterKit

protocol LanchoneteRestService {
    
    func list(callback: @escaping (RSServiceResponse<[Lanchonete]>) -> Void)
    
}

struct LanchoneteService: LanchoneteRestService {
    
    // MARK: - Endpoints
    enum Services: String, RSServiceEndpoint {
        case List = "lanchonetes"
    }
    
    func list(callback: @escaping (RSServiceResponse<[Lanchonete]>) -> Void) {
        
        let uri = RSServiceHelper.mountUrl(withEndPoint: Services.List)
        
        RSServiceHelper.get(url: uri, callbackForObjectArray: callback)
        
    }
    
}
