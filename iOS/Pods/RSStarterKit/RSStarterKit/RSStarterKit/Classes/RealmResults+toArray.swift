//
//  RealmResults+toArray.swift
//  Pods
//
//  Created by Andre M. Della Torre on 7/13/16.
//
//

import RealmSwift

extension Results {
    public func toArray<T>(_ ofType: T.Type) -> [T] {
        var array = [T]()
        for i in 0 ..< count {
            if let result = self[i] as? T {
                array.append(result)
            }
        }
        
        return array
    }
}
