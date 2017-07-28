//
//  RSServiceResponse.swift
//  Pods
//
//  Created by Andre M. Della Torre on 6/22/16.
//
//

import Foundation

public struct RSServiceResponse<T> {
    
    fileprivate let success: Bool
    public let data: T?
    public let error: RSError?
    public let statusCode: Int?
    public let errorMessage: String?
    
    public var isSuccess: Bool { return success }
    
    fileprivate init(success: Bool = false, data: T?, error: RSError?, statusCode: Int?, errorMessage: String?) {
        self.success = success
        self.data = data
        self.error = error
        self.statusCode = statusCode
        self.errorMessage = errorMessage
    }
    
    
    public init(withError error: RSError, statusCode: Int? = nil, errorMessage: String? = nil) {
        self.init(success: false, data: nil, error: error, statusCode: statusCode, errorMessage: errorMessage)
    }
    
    public init(withData data: T, statusCode: Int? = nil) {
        self.init(success: true, data: data, error: nil, statusCode: statusCode, errorMessage: nil)
    }
}
