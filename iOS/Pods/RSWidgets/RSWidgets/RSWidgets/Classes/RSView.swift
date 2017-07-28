//
//  RSView.swift
//  RSWidgets
//
//  Created by Marcus Costa on 1/12/16.
//  Copyright © 2015 redspark. All rights reserved.
//

import UIKit

@IBDesignable
open class RSView: UIView {
    
    // MARK: - Overrides
    required public init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        registerStyleChangedNotification(self)
    }
    
    public override init(frame: CGRect) {
        super.init(frame: frame)
        registerStyleChangedNotification(self)
    }
    
    open override func didMoveToWindow() {
        super.didMoveToWindow()
        applyStyle(animated: false)
    }
    
    // MARK: - Inspectables
    @IBInspectable open var backgroundImage: UIImage! {
        didSet {
            let scaledImage = UIImage.scaleImage(backgroundImage, maxWidth: bounds.size.width, maxHeight: bounds.size.height)
            UIGraphicsBeginImageContext(frame.size)
            scaledImage.draw(in: bounds)
            backgroundColor = UIColor(patternImage: UIGraphicsGetImageFromCurrentImageContext()!)
            UIGraphicsEndImageContext()
        }
    }
    
    @IBInspectable open var cornerRadius: Int = 0 {
        didSet {
            layer.cornerRadius = CGFloat(cornerRadius)
        }
    }
    
    @IBInspectable open var borderWidth: Int = 0 {
        didSet {
            layer.borderWidth = CGFloat(borderWidth)
        }
    }
    
    @IBInspectable open var borderColor: UIColor = UIColor.clear {
        didSet {
            layer.borderColor = borderColor.cgColor
        }
    }
    
    @IBInspectable open var backgroundStyleType: Int = -1
    
    @IBInspectable open var AnimateStyleTransition: Bool = true
}

// MARK: - Stylable
extension RSView: Stylable {
    
    public func StyleDidChange() {
        applyStyle(animated: AnimateStyleTransition)
    }
    
    func applyStyle(animated: Bool) {
        let style = StyleManager.instance.style
        
        if backgroundStyleType >= 0 && backgroundStyleType < style.colors.count {
            UIView.animate(withDuration: animated ? StyleManager.animationDuration : 0.0, animations: { () -> Void in
                self.backgroundColor = style.colors[self.backgroundStyleType].withAlphaComponent(self.backgroundColor?.alpha ?? 1.0)
            })
        }
    }
}
