//
//  RSLabel.swift
//  Pods
//
//  Created by Marcus Costa on 2/18/16.
//
//

import UIKit

@IBDesignable
open class RSLabel: UILabel {

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
    
    @IBInspectable open var backgroundStyleType: Int = -1
    
    @IBInspectable open var textStyleType: Int = -1
    
    @IBInspectable open var AnimateStyleTransition: Bool = true

}

// MARK: - Stylable
extension RSLabel: Stylable {
    
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
        
        if textStyleType >= 0 && textStyleType < style.colors.count {
            UIView.animate(withDuration: animated ? StyleManager.animationDuration : 0.0, animations: { () -> Void in
                self.textColor = style.colors[self.textStyleType].withAlphaComponent(self.textColor?.alpha ?? 1.0)
            })
        }
    }
}
