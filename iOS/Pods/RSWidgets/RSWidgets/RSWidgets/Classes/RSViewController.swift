//
//  RSViewController.swift
//  Pods
//
//  Created by Andre M. Della Torre on 2/23/16.
//
//

import UIKit

open class RSViewController: UIViewController {

    @IBOutlet weak open var myCollectionView: UICollectionView?
    
    open var rotationManager = RSRotationManager()
    
    override open func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override open func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override open func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        rotationManager.RSRM_viewWillAppear(animated)
        
    }
    
    // MARK:  UIContentContainer
    override open func viewWillTransition(to size: CGSize, with coordinator: UIViewControllerTransitionCoordinator) {
        
        rotationManager.RSRM_viewWillTransitionToSize(size, withTransitionCoordinator: coordinator)
    }
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}

// MARK: - UICollectionViewDelegateFlowLayout
extension RSViewController: UICollectionViewDelegateFlowLayout {
    
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
