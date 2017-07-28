//
//  RSServiceEndpoint.swift
//  Pods
//
//  Created by Andre M. Della Torre on 6/22/16.
//
//

import Foundation

public protocol RSServiceEndpoint {
    var endpoint: String { get }
}

extension RSServiceEndpoint where Self: RawRepresentable, Self.RawValue == String {
    
    public var endpoint: String {
        return self.rawValue
    }
    
}