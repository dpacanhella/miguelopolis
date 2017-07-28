//
//  RSCollectionViewController.swift
//  Pods
//
//  Created by André M. Della Torre on 1/28/16.
//
//

import UIKit

open class RSCollectionViewController: UICollectionViewController {

    open var rotationManager = RSRotationManager()
    
    // MARK: View Life Cilcle
    open override func viewDidLoad() {
        super.viewDidLoad()
        
    }
    
    open override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        rotationManager.RSRM_viewWillAppear(animated)
        
    }
    
    // MARK:  UIContentContainer
    override open func viewWillTransition(to size: CGSize, with coordinator: UIViewControllerTransitionCoordinator) {
        
        rotationManager.RSRM_viewWillTransitionToSize(size, withTransitionCoordinator: coordinator)
    }
    
}

// MARK: - UICollectionViewDelegateFlowLayout
extension RSCollectionViewController: UICollectionViewDelegateFlowLayout {
    
    public func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        
        var returnSize = CGSize.zero
        
        if rotationManager.flowLayoutDelegate != nil {
            if RSDevice.isLandscape() {
                let size = rotationManager.flowLayoutDelegate!.collectionView(collectionView, layout: collectionViewLayout, sizeForItemInLandscapeAtIndexPath: indexPath)
                returnSize = CGSize(width: size.height, height: size.width)
            }
            else {
                returnSize = rotationManager.flowLayoutDelegate!.collectionView(collectionView, layout: collectionViewLayout, sizeForItemInPortraitAtIndexPath: indexPath)
            }
        }
        
        return returnSize
        
    }
}


