//
//  RSImageView.swift
//  RSWidgets
//
//  Created by Marcus Costa on 1/12/16.
//  Copyright © 2015 redspark. All rights reserved.
//

import UIKit

@IBDesignable
open class RSImageView: UIImageView {
    
    open var tintMethod: (UIImage, UIColor) -> UIImage = { (image, color) -> UIImage in
        UIGraphicsBeginImageContextWithOptions (image.size, false, UIScreen.main.scale);
        
        let context = UIGraphicsGetCurrentContext()!
        let rect = CGRect(x: 0, y: 0, width: image.size.width, height: image.size.height)
        image.draw(in: rect, blendMode: .normal, alpha: 0.7)
        context.setBlendMode(.destinationAtop)
        color.withAlphaComponent(1).setFill()
        context.fill(rect)
        let image = UIGraphicsGetImageFromCurrentImageContext()!
        UIGraphicsEndImageContext();
        
        return image
    }

    
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
    
    @IBInspectable open var imageStyleType: Int = -1
    
    @IBInspectable open var tintImageStyleType: Int = -1
    
    @IBInspectable open var AnimateStyleTransition: Bool = true
    
}

// MARK: - Stylable
extension RSImageView: Stylable {
    
    public func StyleDidChange() {
        applyStyle(animated: AnimateStyleTransition)
    }
    
    func applyStyle(animated: Bool) {
        let style = StyleManager.instance.style
        
        if imageStyleType >= 0 && imageStyleType < style.images.count {
            var image = style.images[self.imageStyleType]
            
            if tintImageStyleType >= 0 && tintImageStyleType < style.images.count {
                image = tintMethod(image, style.colors[tintImageStyleType])
            }
            
            UIView.transition(with: self,
                duration:animated ? StyleManager.animationDuration : 0.0,
                options: UIViewAnimationOptions.transitionCrossDissolve,
                animations: { self.image =  image},
                completion: nil)
        }
    }
}
