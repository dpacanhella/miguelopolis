//
//  Stylable.swift
//  Pods
//
//  Created by Marcus Costa on 2/1/16.
//
//

import Foundation
import UIKit

public protocol Stylable {
    
    func StyleDidChange()
    
    func registerStyleChangedNotification<T:AnyObject & Stylable>(_ observer: T)
    func unregisterStyleChangedNotification<T:AnyObject & Stylable>(_ observer: T)
}

extension Stylable {
    
    public func registerStyleChangedNotification<T:AnyObject & Stylable>(_ observer: T) {
        NotificationCenter.default.addObserver(observer, selector: (Selector("StyleDidChange")), name: Notification.Name(UIStyleDidChangeNotification), object: nil)
    }
    
    public func unregisterStyleChangedNotification<T:AnyObject & Stylable>(_ observer: T) {
        NotificationCenter.default.removeObserver(observer)
    }
    
}

extension Stylable where Self: UINavigationController {
    
    public var changeStyle: Bool { return true }
    
}
