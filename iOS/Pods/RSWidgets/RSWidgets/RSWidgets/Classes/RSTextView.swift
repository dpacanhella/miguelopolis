//
//  RSTextView.swift
//  Pods
//
//  Created by Andre M. Della Torre on 7/28/16.
//
//

import UIKit

open class RSTextView: UITextView {

    
    @IBInspectable open var lineBottomWidth: Int = 0 
    
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
    
    @IBInspectable open var hintColor: UIColor = UIColor.clear {
        didSet {
            setValue(hintColor, forKeyPath: "_placeholderLabel.textColor")
        }
    }
    
    override open func draw(_ rect: CGRect) {
        super.draw(rect)
        if lineBottomWidth > 0 {
            
            
            borderColor.setStroke()
            
            let context = UIGraphicsGetCurrentContext()
            context!.saveGState()
            context!.setLineWidth(CGFloat(lineBottomWidth))
            context!.move(to: CGPoint(x: 0, y: frame.size.height - CGFloat(lineBottomWidth)))
            context!.addLine(to: CGPoint(x: frame.size.width, y: frame.size.height - CGFloat(lineBottomWidth)))
            context!.strokePath()
            context!.restoreGState()
            
            print(frame)
            print(bounds)
        
        }
    }

}
