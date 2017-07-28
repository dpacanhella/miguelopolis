//
//  UIImage+AssetIdentifier.swift
//  Pods
//
//  Created by Andre M. Della Torre on 6/27/16.
//
//

import Foundation

extension UIImage {
    
    public convenience init!(withImageIdentifier identifier: RSCustomIdentifier) {
        self.init(named: identifier.key)
    }
}

