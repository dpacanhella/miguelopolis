//
//  RSPresentationTableViewCell.swift
//  Pods
//
//  Created by Marcus Costa on 14/02/17.
//
//

import Foundation

public protocol RSPresentationTableViewCell {
    
    func setPresentationValue(presentation: RSStringPresentation)
    
}

extension UITableViewCell: RSPresentationTableViewCell {
    
    public func setPresentationValue(presentation: RSStringPresentation) {
        textLabel?.text = presentation.presentValue
    }
    
}
