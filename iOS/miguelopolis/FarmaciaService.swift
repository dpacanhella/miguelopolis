//
//  FarmaciaService.swift
//  miguelopolis
//
//  Created by Marcos Vinícius Fernandes on 7/5/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import Alamofire
import ObjectMapper
import RSStarterKit

protocol FarmaciaRestService {
    
    func list(callback: @escaping (RSServiceResponse<[Farmacia]>) -> Void)
    
}

struct FarmaciaService: FarmaciaRestService {
    
    // MARK: - Endpoints
    enum Services: String, RSServiceEndpoint {
        case List = "farmacias"
    }
    
    func list(callback: @escaping (RSServiceResponse<[Farmacia]>) -> Void) {
        
        let uri = RSServiceHelper.mountUrl(withEndPoint: Services.List)
        
        RSServiceHelper.get(url: uri, callbackForObjectArray: callback)
        
    }
    
}
