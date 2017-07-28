//
//  UIView+FocusNextView.swift
//  Pods
//
//  Created by Andre M. Della Torre on 24/01/17.
//
//

import Foundation
import UIKit

extension UIView {
    
    public func focusNextViewByTag(insideView view: UIView, forceEndEditing forceEnd: Bool = true) -> UIView? {
        guard let nextView =  view.viewWithTag(tag + 1) else {
            if forceEnd {
                endEditing(true)
            }
            
            return nil
        }
        
        nextView.becomeFirstResponder()
        return nextView
    }
    
}
