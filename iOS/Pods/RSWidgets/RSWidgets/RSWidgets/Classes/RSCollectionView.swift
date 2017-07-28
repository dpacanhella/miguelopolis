//
//  RSCollectionView.swift
//  Pods
//
//  Created by Andre M. Della Torre on 1/29/16.
//
//

import UIKit


open class RSCollectionView: UICollectionView {
    
    open var undoRotation: CGAffineTransform!

    override open func dequeueReusableCell(withReuseIdentifier identifier: String, for indexPath: IndexPath) -> UICollectionViewCell {
        
        let cell = super.dequeueReusableCell(withReuseIdentifier: identifier, for: indexPath)
        
        cell.updateSize(animated: false)
    
        return cell
    }

}
