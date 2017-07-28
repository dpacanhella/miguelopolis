//
//  RSServiceError.swift
//  Pods
//
//  Created by Andre M. Della Torre on 6/22/16.
//
//

import Foundation

public enum RSServiceError: RSError {
    case requestFailure
    case responseObjectError
    
    public var description: String {
        switch self {
        case .requestFailure:
            return String(withCustomIdentifier: RSNetworkingStringIdentifiers.ServiceRequestError)
            
        case .responseObjectError:
            return String(withCustomIdentifier: RSNetworkingStringIdentifiers.ServiceJsonError)
        }
    }
}
