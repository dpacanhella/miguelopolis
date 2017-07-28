//
//  RealmListTransform.swift
//  Pods
//
//  Created by Andre M. Della Torre on 17/01/17.
//
//

import Foundation
import ObjectMapper
import RealmSwift

public struct RealmListTransform<T: RealmSwift.Object>: TransformType where T: Mappable {
    
    public init() { }
    
    public typealias Object = List<T>
    public typealias JSON = Array<Any>
    
    public func transformFromJSON(_ value: Any?) -> List<T>? {
        if let objects = Mapper<T>().mapArray(JSONObject: value) {
            let list = List<T>()
            list.append(objectsIn: objects)
            return list
        }
        return List<T>()
    }
    
    public func transformToJSON(_ value: Object?) -> JSON? {
        return value?.flatMap { $0.toJSON() }
    }
    
}
