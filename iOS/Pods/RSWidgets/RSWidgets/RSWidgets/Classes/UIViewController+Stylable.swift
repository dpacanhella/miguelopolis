//
//  UIViewController+Stylable.swift
//  Pods
//
//  Created by Marcus Costa on 2/2/16.
//
//

import Foundation
import UIKit

extension UINavigationController: Stylable {
    
    public func StyleDidChange() {
        guard changeStyle else {
            return
        }
        
        let style = StyleManager.instance.style
        
        navigationBar.barTintColor = style.navigationBarColor
        navigationBar.tintColor = style.navigationBarTintColor
        navigationBar.titleTextAttributes = [NSForegroundColorAttributeName: style.navigationBarTextColor]
    }
}

extension UINavigationController {
    open override class func initialize() {
        struct Static {
            static var token: Int = 0
        }
        
        // make sure this isn't a subclass
        if self !== UINavigationController.self {
            return
        }
        
        DispatchQueue.once(token: "\(hash())") {
            for method in ["viewDidLoad", "viewDidUnload"] {
                let originalSelector = Selector(method)
                let swizzledSelector = Selector("rs_\(method)")
                
                let originalMethod = class_getInstanceMethod(self, originalSelector)
                let swizzledMethod = class_getInstanceMethod(self, swizzledSelector)
                
                let didAddMethod = class_addMethod(self, originalSelector, method_getImplementation(swizzledMethod), method_getTypeEncoding(swizzledMethod))
                
                if didAddMethod {
                    class_replaceMethod(self, swizzledSelector, method_getImplementation(originalMethod), method_getTypeEncoding(originalMethod))
                } else {
                    method_exchangeImplementations(originalMethod, swizzledMethod)
                }
            }
        }
    }
    
    // MARK: - Methods Swizzling
    func rs_viewDidLoad() {
        
#if !TARGET_INTERFACE_BUILDER
        self.rs_viewDidLoad()
        
        registerStyleChangedNotification(self)
        debugPrint("Register UINavigationController in style notification")
#endif
    }
    
    func rs_viewDidUnload() {
        self.rs_viewDidUnload()
        registerStyleChangedNotification(self)
        debugPrint("Unregister UINavigationController in style notification")
    }
}

public extension DispatchQueue {
    
    private static var _onceTracker = [String]()
    
    /**
     Executes a block of code, associated with a unique token, only once.  The code is thread safe and will
     only execute the code once even in the presence of multithreaded calls.
     
     - parameter token: A unique reverse DNS style name such as com.vectorform.<name> or a GUID
     - parameter block: Block to execute once
     */
    public class func once(token: String, block:(Void)->Void) {
        objc_sync_enter(self); defer { objc_sync_exit(self) }
        
        if _onceTracker.contains(token) {
            return
        }
        
        _onceTracker.append(token)
        block()
    }
}
