//
//  UIViewController+CloseKeyboard.swift
//  Pods
//
//  Created by Andre M. Della Torre on 24/01/17.
//
//

import Foundation

extension UIViewController {
    
    public func registerCloseKeyboardWhenTapView(on containerView: UIView? = nil) {
        let tap = UITapGestureRecognizer(target: self, action: #selector(forceKeyboardClose))
        
        let container = containerView ?? view!
        
        let gestureView = UIView(frame: container.frame)
        gestureView.backgroundColor = UIColor.clear
        gestureView.isUserInteractionEnabled = true
        gestureView.addGestureRecognizer(tap)
        
        
        container.insertSubview(gestureView, at: 0)
    }
    
    public func forceKeyboardClose() {
        view.endEditing(true)
    }
}
