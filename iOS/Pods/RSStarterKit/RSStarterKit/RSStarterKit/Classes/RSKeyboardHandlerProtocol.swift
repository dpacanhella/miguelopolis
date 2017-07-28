//
//  RSKeyboardHandlerProtocol.swift
//  Pods
//
//  Created by Andre M. Della Torre on 24/01/17.
//
//

import Foundation
import UIKit

public protocol RSKeyboardHandlerProtocol {
    
    func keyboardWillShow(_ notification: Notification, scrollView: UIScrollView, scrollToView: UIView?)
    func keyboardWillHide(_ notification: Notification, scrollView: UIScrollView)
    
    func keyboardWillShow(_ notification: Notification, scrollView: UIScrollView, scrollToView view: UIView?, changeInsets: Bool)
    
    func keyboardWillShow(_ notification: Notification, view: UIView)
    func keyboardWillHide(_ notification: Notification, view: UIView)

}

extension RSKeyboardHandlerProtocol where Self: UIViewController {
    
    public func keyboardWillShow(_ notification: Notification, scrollView: UIScrollView, scrollToView view: UIView? = nil) {
        
        keyboardWillShow(notification, scrollView: scrollView, scrollToView: view, changeInsets: true)
    }
    
    public func keyboardWillShow(_ notification: Notification, scrollView: UIScrollView, scrollToView view: UIView? = nil, changeInsets: Bool) {
        
        let info: NSDictionary = (notification as NSNotification).userInfo! as NSDictionary
        let value: NSValue = info.value(forKey: UIKeyboardFrameBeginUserInfoKey) as! NSValue
        let keyboardSize: CGSize = value.cgRectValue.size
        let contentInsets: UIEdgeInsets = changeInsets ? UIEdgeInsetsMake(0.0, 0.0, keyboardSize.height, 0.0) : UIEdgeInsets()
        scrollView.contentInset = contentInsets
        scrollView.scrollIndicatorInsets = contentInsets
        
        if let view = view {
            scrollView.scrollToView(view)
        }
    }
    
    public func keyboardWillShow(_ notification: Notification, view: UIView) {
        
        let info: NSDictionary = (notification as NSNotification).userInfo! as NSDictionary
        let value: NSValue = info.value(forKey: UIKeyboardFrameBeginUserInfoKey) as! NSValue
        let keyboardSize: CGSize = value.cgRectValue.size
        
        UIView.animate(withDuration: info.value(forKey: UIKeyboardAnimationDurationUserInfoKey) as! TimeInterval) {
            view.frame = CGRect(x: view.frame.origin.x, y: view.frame.origin.y - keyboardSize.height, width: view.frame.size.width, height: view.frame.size.height)
            view.superview?.layoutIfNeeded()
        }
    }
    
    public func keyboardWillHide(_ notification: Notification, view: UIView) {
        
        let info: NSDictionary = (notification as NSNotification).userInfo! as NSDictionary
        let value: NSValue = info.value(forKey: UIKeyboardFrameBeginUserInfoKey) as! NSValue
        let keyboardSize: CGSize = value.cgRectValue.size
        
        UIView.animate(withDuration: info.value(forKey: UIKeyboardAnimationDurationUserInfoKey) as! TimeInterval) {
            view.frame = CGRect(x: view.frame.origin.x, y: view.frame.origin.y + keyboardSize.height, width: view.frame.size.width, height: view.frame.size.height)
            view.superview?.layoutIfNeeded()
        }
    }
    
    public func keyboardWillHide(_ notification: Notification, scrollView: UIScrollView) {
        let contentInsets: UIEdgeInsets = UIEdgeInsets.zero
        scrollView.contentInset = contentInsets
        scrollView.scrollIndicatorInsets = contentInsets
    }
}

