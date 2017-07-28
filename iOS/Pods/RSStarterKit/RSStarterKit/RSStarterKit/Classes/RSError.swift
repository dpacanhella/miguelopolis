//
//  RSCustomError.swift
//  Pods
//
//  Created by Andre M. Della Torre on 6/22/16.
//
//

import Foundation

public protocol RSError: Error, CustomStringConvertible {
    
}

extension Error {
    
    var description: String { return "\(self)" }
    
}
