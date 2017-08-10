//
//  DetalhesService.swift
//  miguelopolis
//
//  Created by infra on 10/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import Alamofire
import ObjectMapper
import RSStarterKit

protocol PromocoesRestService {
    
    func list(id: Int, callback: @escaping (RSServiceResponse<[Promocao]>) -> Void)
    
}

struct PromocoesService: PromocoesRestService {
    
    func list(id: Int, callback: @escaping (RSServiceResponse<[Promocao]>) -> Void) {
        var uri = RSServiceHelper.mountUrl(withEndPoint: Services.List)
//        print(id)
        
        let idString = String(id)
        uri = String(format: uri, idString)
        
        RSServiceHelper.get(url: uri, callbackForObjectArray: callback)
    }
    
    
    // MARK: - Endpoints
    enum Services: String, RSServiceEndpoint {
        case List = "/promocoes/farmaciaId/%@"
    }
    
    
}
