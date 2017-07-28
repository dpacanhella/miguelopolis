//
//  UICollectionView+CellIdentifier.swift
//  Pods
//
//  Created by Andre M. Della Torre on 6/27/16.
//
//

import Foundation
import UIKit

extension UICollectionView {
    
    public func dequeueReusableCellWithReuseIdentifier(_ identifier: RSCustomIdentifier, forIndexPath: IndexPath) -> UICollectionViewCell {
        return dequeueReusableCell(withReuseIdentifier: identifier.key, for: forIndexPath)
    }
    
    public func registerNib(_ nib: UINib?, forCellWithReuseIdentifier identifier: RSCustomIdentifier) {
        return register(nib, forCellWithReuseIdentifier: identifier.key)
    }
}
