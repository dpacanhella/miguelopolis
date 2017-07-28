//
//  RSCollectionViewCellProtocol.swift
//  Pods
//
//  Created by Andre M. Della Torre on 2/5/16.
//
//

import UIKit
import Darwin

@objc public protocol RSCollectionViewCellProtocol {
    
    
    var rsViewContainer: UIView? {get}
    
    var rsConstraintHeight: NSLayoutConstraint? {get}
    var rsConstraintWidth: NSLayoutConstraint? {get}
    
}

extension RSCollectionViewCellProtocol where Self:UICollectionViewCell {
    
    public func updateSize(animated: Bool, completion: ((Bool) -> ())? = nil) {
        
        guard let constraintHeight = rsConstraintHeight, let constraintWidth = rsConstraintWidth, let viewContainer = rsViewContainer else { return }
        
        var rsTransform = CGAffineTransform()
        
        if RSDevice.isLandscape() {
            constraintHeight.constant = CGFloat(width)
            constraintWidth.constant = CGFloat(height)
            
            rsTransform = CGAffineTransform(rotationAngle: CGFloat(M_PI_2))
        }
        else {
            constraintHeight.constant = CGFloat(height)
            constraintWidth.constant = CGFloat(width)
            
            rsTransform = CGAffineTransform(rotationAngle: 0)
        }
        
        let duration = animated ? 0.5 : 0.0
        
        UIView.animate(withDuration: duration,
            animations: { () -> Void in
                viewContainer.transform = rsTransform
                viewContainer.layoutIfNeeded()
            }, completion: { (finished) -> Void in
                if completion != nil {
                    completion!(finished)
                }
        }) 
    }
}

extension UICollectionViewCell: RSCollectionViewCellProtocol {
    
    public var rsViewContainer: UIView? {return nil}
    
    public var rsConstraintHeight: NSLayoutConstraint? { return nil }
    public var rsConstraintWidth: NSLayoutConstraint? { return nil }
}
