//
//  RSScrollViewConstraintsProtocol.swift
//  Pods
//
//  Created by Andre M. Della Torre on 24/01/17.
//
//

import Foundation

public protocol RSScrollViewConstraintsProtocol {
    
    var contentView: UIView! { get set }
    func setupScrollViewConstraints()
    
}

extension RSScrollViewConstraintsProtocol where Self: UIViewController {
 
    public func setupScrollViewConstraints() {
        
        view.addConstraint(NSLayoutConstraint(item: contentView, attribute: .left, relatedBy: .equal, toItem: view, attribute: .left, multiplier: 1, constant: 0))
        
        view.addConstraint(NSLayoutConstraint(item: contentView, attribute: .right, relatedBy: .equal, toItem: view, attribute: .right, multiplier: 1, constant: 0))
        
    }
    
}
