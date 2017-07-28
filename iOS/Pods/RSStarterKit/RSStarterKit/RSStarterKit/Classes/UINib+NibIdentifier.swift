//
//  UINib+NibIdentifier.swift
//  Pods
//
//  Created by Andre M. Della Torre on 6/27/16.
//
//

import Foundation
import UIKit

extension UINib {
    
    public convenience init(identifier: RSCustomIdentifier, bundle: Bundle?) {
        self.init(nibName: identifier.key, bundle: bundle)
    }
}
