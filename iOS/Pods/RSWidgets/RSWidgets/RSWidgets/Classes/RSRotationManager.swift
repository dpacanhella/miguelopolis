//
//  RSRotationManager.swift
//  Pods
//
//  Created by Andre M. Della Torre on 2/22/16.
//
//

import Foundation

public protocol RSCollectionViewDelegateFlowLayout {
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemInLandscapeAtIndexPath indexPath: IndexPath) -> CGSize
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemInPortraitAtIndexPath indexPath: IndexPath) -> CGSize
}

open class RSRotationManager {

    open var invertRotation = false
    open var flowLayoutDelegate: RSCollectionViewDelegateFlowLayout?
    open var customCollectionView: RSCollectionView?
    open var navigationController: UINavigationController?
    
    fileprivate var currentBounds = CGRect.zero
    fileprivate var lastOrientation = RSDevice.Orientation.unknown
    
    open func RSRM_Start() {

        lastOrientation = .unknown
        customCollectionView!.undoRotation = customCollectionView!.transform
        
    }
    
    func RSRM_viewWillAppear(_ animated: Bool) {

        if invertRotation && self.lastOrientation == .unknown {
            self.lastOrientation = .portrait

            if RSDevice.isLandscape() {

                let newBounds = CGRect(x: 0, y: 0, width: self.customCollectionView!.bounds.height, height: self.customCollectionView!.bounds.width)

                self.currentBounds = newBounds
                self.doTranformRotation()
                self.doCellRotation(animated: false)

            }
        }
        
        customCollectionView?.reloadData()
        
    }
    
    func RSRM_viewWillTransitionToSize(_ size: CGSize, withTransitionCoordinator coordinator: UIViewControllerTransitionCoordinator) {
        
        if invertRotation {
            
            if self.lastOrientation == .portrait || RSDevice.deviceOrientation() == .portrait {
                self.customCollectionView?.collectionViewLayout.invalidateLayout()
            }
            
            self.currentBounds = self.customCollectionView!.bounds
            
            coordinator.animate(alongsideTransition: {_ in
                self.doTranformRotation()
                self.doCellRotation(animated: true)

                if self.navigationController != nil {
                    self.navigationController!.loadView()
                }
                },
                completion: { _ in
                    if let superview = self.customCollectionView?.superview {
                        
                        self.customCollectionView!.frame = (self.customCollectionView?.superview!.frame)!
                        self.customCollectionView?.collectionViewLayout.invalidateLayout()
                    }

                }
                
            )
        
        }
    }
    
    
    // MARK: Private Methods
    fileprivate func getTransform() -> CGAffineTransform {
        var angle: Double
        
        switch (self.lastOrientation, RSDevice.deviceOrientation()) {
        case (.portrait, .landscapeLeft), (.portrait, .landscapeRight):
            angle = 90.0
        case (.landscapeRight, .portrait), (.landscapeLeft, .portrait):
            angle = -90.0
        default:
            angle = 0.0
        }
        
        let angleRad = angle * M_PI / 180.0
        
        return CGAffineTransform(rotationAngle: CGFloat(angleRad))
    }
    
    fileprivate func doTranformRotation () {

        let newTransform = getTransform()
        let invertedRotation = newTransform.inverted()
        
        self.customCollectionView!.transform = self.customCollectionView!.transform.concatenating(invertedRotation )
        self.customCollectionView!.undoRotation =  customCollectionView!.undoRotation.concatenating(newTransform)
        self.customCollectionView!.bounds = self.currentBounds
        
    }
    
    fileprivate func doCellRotation(animated: Bool) {
        
        for cell in self.customCollectionView!.visibleCells {
            cell.updateSize(animated: true)
        }
        
        self.lastOrientation = RSDevice.deviceOrientation()
    }
    
}






