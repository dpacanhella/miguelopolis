//
//  UIScrollView+ScrollToView.swift
//  Pods
//
//  Created by Andre M. Della Torre on 24/01/17.
//
//

import Foundation
import UIKit

extension UIScrollView {
    
    public func scrollToView(_ view: UIView) {
        var rect = view.convert(view.bounds, to: self)
        rect.size.height += 20
        scrollRectToVisible(rect, animated: true)
    }
    
}
