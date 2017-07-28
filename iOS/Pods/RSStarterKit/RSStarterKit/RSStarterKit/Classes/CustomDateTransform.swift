//
//  CustomDateTransform.swift
//  Pods
//
//  Created by Andre M. Della Torre on 17/01/17.
//
//

import Foundation
import UIKit
import ObjectMapper

public struct CustomDateTransform: TransformType {
    
    public static var stringDateFormatter = ["yyyy-MM-dd'T'HH:mm:ss.SSSZ"]
    
    public typealias Object = Date
    public typealias JSON = String
    
    public init() {}
    
    fileprivate var dateFormatter = DateFormatter()
    
    public func transformFromJSON(_ value: Any?) -> Date? {
        if let strDate = value as? String {
            var convertedDate: Date?
            
            
            for idx in 0..<CustomDateTransform.stringDateFormatter.count {
                dateFormatter.dateFormat = CustomDateTransform.stringDateFormatter[idx]
                if let date = dateFormatter.date(from: strDate) {
                    convertedDate = date
                    break
                }
            }
            
            return convertedDate
        }
        
        return nil
    }
    
    public func transformToJSON(_ value: Date?) -> String? {
        if let date = value {
            dateFormatter.dateFormat = CustomDateTransform.stringDateFormatter[0]
            return dateFormatter.string(from: date)
        }
        return nil
    }
    
}
