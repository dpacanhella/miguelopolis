//
//  ImagemLojaService.swift
//  miguelopolis
//
//  Created by infra on 16/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import Alamofire
import ObjectMapper
import RSStarterKit

protocol ImagemLojaRestService {
    
    func list(id: Int, callback: @escaping (RSServiceResponse<[ImagemLoja]>) -> Void)
    
}

struct ImagemLojaService: ImagemLojaRestService {
    
    func list(id: Int, callback: @escaping (RSServiceResponse<[ImagemLoja]>) -> Void) {
        var uri = RSServiceHelper.mountUrl(withEndPoint: Services.List)
        //        print(id)
        
        let idString = String(id)
        uri = String(format: uri, idString)
        
        RSServiceHelper.get(url: uri, callbackForObjectArray: callback)
    }
    
    
    // MARK: - Endpoints
    enum Services: String, RSServiceEndpoint {
        case List = "/imagens-lojas/lojaId/%@"
    }
    
    
}
