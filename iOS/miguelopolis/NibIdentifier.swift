//
//  NibIdentifier.swift
//  starter-kit
//  miguelopolis
//
//  Created by Marcos Vinícius Fernandes on 7/5/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import UIKit
import RSStarterKit

enum NibIdentifier: String, RSCustomIdentifier {
    
    case defaultNib = "DefaultNib"
    case slideMenuCell = "SlideMenuCell"

}

extension NibIdentifier {
    
    func getNib(bundle: Bundle? = nil) -> UINib {
        return UINib(identifier: self, bundle: bundle)
    }
    
}


