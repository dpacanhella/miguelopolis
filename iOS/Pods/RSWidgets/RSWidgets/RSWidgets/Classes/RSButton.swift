//
//  CustomButton.swift
//  RSWidgets
//
//  Created by Marcus Costa on 12/28/15.
//  Copyright © 2015 redspark. All rights reserved.
//

import UIKit

@IBDesignable
open class RSButton: UIButton {
    
    fileprivate var configuredSpaceBetweenImageAndText: CGFloat = 0
    
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
    
    open override func setTitle(_ title: String?, for state: UIControlState) {
        super.setTitle(title, for: state)
        spaceBetweenImageAndText = configuredSpaceBetweenImageAndText
    }
    
    open override func setImage(_ image: UIImage?, for state: UIControlState) {
        super.setImage(image, for: state)
        
        if image == nil {
            let lastValue = configuredSpaceBetweenImageAndText
            spaceBetweenImageAndText = 0
            configuredSpaceBetweenImageAndText = lastValue
        }
        else {
            spaceBetweenImageAndText = configuredSpaceBetweenImageAndText
        }
    }
    
    // MARK: - Inspectables
    @IBInspectable open var textStyleType: Int = -1
    
    @IBInspectable open var backgroundStyleType: Int = -1
    
    @IBInspectable open var AnimateStyleTransition: Bool = true
    
    @IBInspectable open var spaceBetweenImageAndText: CGFloat = 0 {
        didSet {
            guard let buttonImageView = imageView, let buttonTitle = titleLabel else {
                return
            }
            
            configuredSpaceBetweenImageAndText = spaceBetweenImageAndText
            
            let imageSize = buttonImageView.frame.size
            let titleSize = buttonTitle.frame.size
            
            switch spaceBetweenImageAndText {
            case let position where position > 0:
                let spacing = spaceBetweenImageAndText + (spaceBetweenImageAndText.truncatingRemainder(dividingBy: 2) != 0 ? 1 : 0)
                
                titleEdgeInsets = UIEdgeInsets(top: titleEdgeInsets.top, left: -imageSize.width - spacing / 2, bottom: titleEdgeInsets.bottom, right: imageSize.width)
                imageEdgeInsets = UIEdgeInsets(top: imageEdgeInsets.top, left: titleSize.width + spacing / 2, bottom: imageEdgeInsets.bottom, right: -titleSize.width)
                
            case let position where position < 0:
                titleEdgeInsets = UIEdgeInsets(top: titleEdgeInsets.top, left: 0.0, bottom: titleEdgeInsets.bottom, right: spaceBetweenImageAndText)
                imageEdgeInsets = UIEdgeInsets(top: imageEdgeInsets.top, left: spaceBetweenImageAndText, bottom: imageEdgeInsets.bottom, right: 0)
                
            default:
                titleEdgeInsets = UIEdgeInsets()
                imageEdgeInsets = UIEdgeInsets()
            }
        }
    }
    
    @IBInspectable open var backgroundImage: UIImage! {
        didSet {
            UIGraphicsBeginImageContext(frame.size)
            backgroundImage.draw(in: bounds)
            if let pattern = UIGraphicsGetImageFromCurrentImageContext() {
                backgroundColor = UIColor(patternImage: pattern)
            }
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
}

// MARK: - Stylable
extension RSButton: Stylable {
    
    public func StyleDidChange() {
        applyStyle(animated: AnimateStyleTransition)
    }
    
    func applyStyle(animated: Bool) {
        let style = StyleManager.instance.style
        
        if textStyleType >= 0 && textStyleType < style.colors.count {
            UIView.animate(withDuration: animated ? 2.3 : 0.0, animations: { () -> Void in
                let lastColor = self.titleColor(for: UIControlState())
                self.setTitleColor(style.colors[self.textStyleType].withAlphaComponent(lastColor?.alpha ?? 1.0), for: UIControlState())
            })
        }
        
        if backgroundStyleType >= 0 && backgroundStyleType < style.colors.count {
            UIView.animate(withDuration: animated ? StyleManager.animationDuration : 0.0, animations: { () -> Void in
                self.backgroundColor = style.colors[self.backgroundStyleType].withAlphaComponent(self.backgroundColor?.alpha ?? 1.0)
            })
        }
    }
}



























