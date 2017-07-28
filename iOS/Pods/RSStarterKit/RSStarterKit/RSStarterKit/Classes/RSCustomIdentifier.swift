//
//  RSCustomIdentifier.swift
//  Pods
//
//  Created by Andre M. Della Torre on 6/27/16.
//
//

import Foundation

public protocol RSCustomIdentifier {
    var key: String { get }
}

extension RSCustomIdentifier where Self: RawRepresentable, Self.RawValue == String {

    public var key: String { return self.rawValue }
    
}