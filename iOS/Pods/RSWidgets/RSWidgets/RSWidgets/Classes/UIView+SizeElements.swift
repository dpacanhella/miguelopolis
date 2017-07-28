//
//  UIView+SizeElements.swift
//  Pods
//
//  Created by Andre M. Della Torre on 1/22/16.
//
//

import Foundation
import UIKit

extension UIView {
    
    public var height: Double {
        get { return Double(self.frame.size.height) }
        set(value) { self.frame.size.height = CGFloat(value) }
    }
 
    public var width: Double {
        get { return Double(self.frame.size.width) }
        set(value) { self.frame.size.width = CGFloat(value) }
    }
    
    public var x: Double {
        get { return Double(self.frame.origin.x) }
        set(value) { self.frame.origin.x = CGFloat(value) }
    }
    
    public var y: Double {
        get { return Double(self.frame.origin.y) }
        set(value) { self.frame.origin.y = CGFloat(value) }
    }
}