//
//  UITableView+CellIdentifier.swift
//  Pods
//
//  Created by Andre M. Della Torre on 6/27/16.
//
//

import Foundation
import UIKit

extension UITableView {
    
    public func dequeueReusableCellWithIdentifier(_ identifier: RSCustomIdentifier, forIndexPath: IndexPath) -> UITableViewCell {
        return dequeueReusableCell(withIdentifier: identifier.key, for: forIndexPath)
    }
    
    public func registerNib(_ nib: UINib?, forCellReuseIdentifier identifier: RSCustomIdentifier) {
        return register(nib, forCellReuseIdentifier: identifier.key)
    }
    
    public func dequeueReusableHeaderFooterViewWithIdentifier(_ identifier: RSCustomIdentifier) -> UIView? {
        return dequeueReusableHeaderFooterView(withIdentifier: identifier.key)
    }
    
    public func registerNib(_ nib: UINib?,  forHeaderFooterViewReuseIdentifier identifier: RSCustomIdentifier) {
        return register(nib, forHeaderFooterViewReuseIdentifier: identifier.key)
    }
}
    
