//
//  String+LocalizedKey.swift
//  Pods
//
//  Created by Andre M. Della Torre on 6/27/16.
//
//

import Foundation
import UIKit

extension String  {
    
    public init(withCustomIdentifier identifier: RSCustomIdentifier) {
        self.init(NSLocalizedString(identifier.key, comment: ""))!
    }
    
    public init(withCustomIdentifier identifier: RSCustomIdentifier, comment: String) {
        self.init(NSLocalizedString(identifier.key, comment: comment))!
    }
    
}
