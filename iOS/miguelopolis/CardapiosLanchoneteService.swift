//
//  CardapiosLanchoneteService.swift
//  miguelopolis
//
//  Created by infra on 15/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import Alamofire
import ObjectMapper
import RSStarterKit

protocol CardapiosLanchoneteRestService {
    
    func list(id: Int, callback: @escaping (RSServiceResponse<[Cardapios]>) -> Void)
    
}

struct CardapiosLanchoneteService: CardapiosLanchoneteRestService {
    
    func list(id: Int, callback: @escaping (RSServiceResponse<[Cardapios]>) -> Void) {
        var uri = RSServiceHelper.mountUrl(withEndPoint: Services.List)
        //        print(id)
        
        let idString = String(id)
        uri = String(format: uri, idString)
        
        RSServiceHelper.get(url: uri, callbackForObjectArray: callback)
    }
    
    
    // MARK: - Endpoints
    enum Services: String, RSServiceEndpoint {
        case List = "/lanchonetes/cardapios/%@"
    }
    
    
}
