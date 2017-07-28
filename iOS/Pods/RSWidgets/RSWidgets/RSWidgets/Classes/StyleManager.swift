//
//  StyleManager.swift
//  Pods
//
//  Created by Marcus Costa on 2/1/16.
//
//

import UIKit

public var CurrentStyle: Style {
    return StyleManager.instance.style
}

open class StyleManager {

    public enum StyleType: Int {
        case none = 0, primary, secondary, both
    }
    
    open static let instance = StyleManager()
    
    open static var animationDuration = 1.3
    
    open var style = Style()
    open var isInitialized = false
    
    var defaultNotificationCenter: NotificationCenter {
        return NotificationCenter.default
    }
    
    open static func initalize() {
        guard !instance.isInitialized else {
            return
        }
        
        instance.defaultNotificationCenter.post(name: Notification.Name(rawValue: UIStyleDidChangeNotification), object: nil)
        instance.changeAppearance()
        instance.isInitialized = true
    }

    open static func initalize(withStyle style: Style) {
        guard !instance.isInitialized else {
            return
        }
        
        instance.style = style
        initalize()
    }
    
    open static func changeToStyle(_ style: Style) {
        if instance.style != style {
            instance.style = style
            instance.changeAppearance()
            instance.defaultNotificationCenter.post(name: Notification.Name(rawValue: UIStyleDidChangeNotification), object: nil)
        }
    }
    
    fileprivate func changeAppearance() {
        let instance = StyleManager.instance
        
        let navigationBarAppearace = UINavigationBar.appearance()
        navigationBarAppearace.barTintColor = instance.style.navigationBarColor
        navigationBarAppearace.tintColor = instance.style.navigationBarTintColor
        navigationBarAppearace.titleTextAttributes = [NSForegroundColorAttributeName: instance.style.navigationBarTextColor]
    }
}















