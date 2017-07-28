//
//  NSUserDefaults+KeyIdentifier.swift
//  Pods
//
//  Created by Andre M. Della Torre on 6/27/16.
//
//

import Foundation

extension UserDefaults {
    
    public func arrayForKey(_ keyIdentifier: RSCustomIdentifier) -> [AnyObject]?{
        return array(forKey: keyIdentifier.key) as [AnyObject]?
    }
    
    public func objectForKey(_ keyIdentifier: RSCustomIdentifier) -> AnyObject? {
        return object(forKey: keyIdentifier.key) as AnyObject?
    }
    
    public func setObject(_ value: AnyObject?, forKeyIdentifier: RSCustomIdentifier){
        set(value, forKey: forKeyIdentifier.key)
    }
    
    public func setValue(_ value: AnyObject?, forKeyIdentifier: RSCustomIdentifier){
        setValue(value, forKey: forKeyIdentifier.key)
    }
    
    public func stringForKey(_ keyIdentifier: RSCustomIdentifier) -> String?{
        return string(forKey: keyIdentifier.key)
    }
    
    public func removeObjectForKey(_ keyIdentifier: RSCustomIdentifier){
        removeObject(forKey: keyIdentifier.key)
    }
}
