//
//  RSHttpStatusCode.swift
//  Pods
//
//  Created by Andre M. Della Torre on 6/22/16.
//
//

import Foundation

public enum RSHttpStatusCode: Int {
    case success = 200
    case created = 201
    case accepted = 202
    case reset = 205
    case moved = 301
    case found = 302
    case invalidRequest = 400
    case notAuthorized = 401
    case prohibided = 403
    case notFound = 404
    case internalError = 500
    case badGateway = 502
    
    
    public var code: Int {
        return rawValue
    }
}
