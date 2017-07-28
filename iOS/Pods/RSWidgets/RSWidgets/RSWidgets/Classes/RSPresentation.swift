//
//  RSPresentation.swift
//  Pods
//
//  Created by Marcus Costa on 14/02/17.
//
//

import Foundation

public protocol RSStringPresentation {
    
    var presentValue: String { get }
    
}

public extension RSStringPresentation where Self: CustomStringConvertible {
    
    public var presentValue: String {
        return description
    }
    
}

public extension RSStringPresentation where Self: RawRepresentable, Self.RawValue == String {
    
    public var presentValue: String {
        return self.rawValue
    }
    
}

public extension RSStringPresentation where Self: CustomStringConvertible, Self: RawRepresentable, Self.RawValue == String {
    
    public var presentValue: String {
        return description
    }
    
}

// MARK: - Base class extensions
extension String: RSStringPresentation {
    
    public var presentValue: String {
        return self
    }
    
}

extension Int: RSStringPresentation {
    
    public var presentValue: String {
        return "\(self)"
    }
    
}





















