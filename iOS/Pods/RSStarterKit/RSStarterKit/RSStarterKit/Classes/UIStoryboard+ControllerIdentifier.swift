//
//  UIStoryboard+ControllerIdentifier.swift
//  Pods
//
//  Created by Andre M. Della Torre on 6/27/16.
//
//

import Foundation
import UIKit

extension UIStoryboard {
    
    public func instantiateViewControllerWithIdentifier(_ identifier: RSCustomIdentifier) -> UIViewController {
        return instantiateViewController(withIdentifier: identifier.key)
    }
    
}
