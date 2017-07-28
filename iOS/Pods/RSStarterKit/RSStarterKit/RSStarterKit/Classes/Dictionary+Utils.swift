//
//  Dictionary+Utils.swift
//  Pods
//
//  Created by Marcus Costa on 30/09/16.
//
//

import Foundation

/**
 Concat dictionaries
 
 */
public func += <K, V> ( left: inout [K:V], right: [K:V]) {
    for (k, v) in right {
        left.updateValue(v, forKey: k)
    }
}
