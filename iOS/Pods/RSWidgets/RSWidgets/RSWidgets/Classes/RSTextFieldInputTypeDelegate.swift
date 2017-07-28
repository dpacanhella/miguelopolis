//
//  RSTextFieldInputTypeDelegate.swift
//  Pods
//
//  Created by Andre M. Della Torre on 06/03/17.
//
//

import Foundation

public protocol RSTextFieldInputTypeDelegate {
    
    func RSTextFieldInputType(_ textField: RSTextField, didConfirmWithValue value: String)
    func RSTextFieldInputType(_ textField: RSTextField, didConfirmWithIndex index: Int)
    func RSTextFieldInputType(_ textField: RSTextField, didConfirmWithDate date: Date)
    func RSTextFieldInputTypeDidCancel(_ textField: RSTextField)
    func RSTextFieldInputTypeDidClean(_ textField: RSTextField)
    
}

extension RSTextFieldInputTypeDelegate {
    
    public func RSTextFieldInputType(_ textField: RSTextField, didConfirmWithValue value: String) { }
    public func RSTextFieldInputType(_ textField: RSTextField, didConfirmWithIndex index: Int) { }
    public func RSTextFieldInputType(_ textField: RSTextField, didConfirmWithDate date: Date) { }
    public func RSTextFieldInputTypeDidCancel(_ textField: RSTextField) { }
    public func RSTextFieldInputTypeDidClean(_ textField: RSTextField) { }
    
}
