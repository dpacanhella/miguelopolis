//
//  RSSearchable.swift
//  Pods
//
//  Created by Marcus Costa on 14/02/17.
//
//

import Foundation

public protocol RSSearchable {
    
    func contains(term: String) -> Bool
    
    func prepareSearchableValue(value: String) -> String
    
}

extension RSSearchable {
    
    public func prepareSearchableValue(value: String) -> String {
        let unaccentedName = value.folding(options: .diacriticInsensitive, locale: Locale.current)
        let lowercased = unaccentedName.lowercased()
        
        return lowercased
    }
    
}

extension RSSearchable where Self: RawRepresentable, Self.RawValue == String {
    
    public func contains(term: String) -> Bool {
        return prepareSearchableValue(value: rawValue).contains(prepareSearchableValue(value: term))
    }
    
}

extension RSSearchable where Self: CustomStringConvertible {
    
    public func contains(term: String) -> Bool {
        return prepareSearchableValue(value: description).contains(prepareSearchableValue(value: term))
    }
    
}

extension RSSearchable where Self: CustomStringConvertible, Self: RawRepresentable, Self.RawValue == String {
    
    public func contains(term: String) -> Bool {
        return prepareSearchableValue(value: description).contains(prepareSearchableValue(value: term))
    }
    
}

extension String: RSSearchable {
    
    public func contains(term: String) -> Bool {
        return prepareSearchableValue(value: self).contains(prepareSearchableValue(value: term))
    }
    
}

extension Int: RSSearchable {
    
    public func contains(term: String) -> Bool {
        return "\(self)".contains(term)
    }
    
}
