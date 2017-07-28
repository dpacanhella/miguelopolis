//
//  RSServiceHelper.swift
//  Pods
//
//  Created by Andre M. Della Torre on 6/22/16.
//
//

import Foundation
import Alamofire
import ObjectMapper

public struct RSServiceHelper {
    
    static public var baseUrl: String {
        return AppConfig.config(withKey: .ServiceUrl)
    }
    
    static public func mountUrl(_ url: String = baseUrl, withEndPoint service: RSServiceEndpoint) -> String {
        return "\(baseUrl)/\(service.endpoint)"
    }
    
    static public func mountUrl(_ url: String = baseUrl, withEndPoint service: RSServiceEndpoint, pathParameters parameters: String...) -> String {
        return String(format: mountUrl(url, withEndPoint: service), parameters)
    }
    
    static public func addParams(_ params: [String: Any], onUrl url: String = baseUrl) -> String {
        return "\(url)?\(params.map{(k, v) in "\(k)=\(v)"}.joined(separator: "&"))"
    }
    
    static public func restManager() -> Alamofire.SessionManager {
        let debbugerMode: Bool = AppConfig.config(withKey: .ServiceDebbugerMode)
        if(debbugerMode) {
            return Alamofire.SessionManager.default
        }
        
        return Alamofire.SessionManager.default
    }
}

// MARK:- Service request brigde
extension RSServiceHelper {
    
    // MARK: - Boolean
    static public func post(url: String, parameters: Parameters? = nil, headers: HTTPHeaders? = nil, callbackForString callback: @escaping (RSServiceResponse<String>) -> Void) {
        request(url: url, method: .post, parameters: parameters, headers: headers, callbackForString: callback)
    }
    
    static public func put(url: String, parameters: Parameters? = nil, headers: HTTPHeaders? = nil, callbackForString callback: @escaping (RSServiceResponse<String>) -> Void) {
        request(url: url, method: .put, parameters: parameters, headers: headers, callbackForString: callback)
    }
    
    static public func get(url: String, parameters: Parameters? = nil, headers: HTTPHeaders? = nil, callbackForString callback: @escaping (RSServiceResponse<String>) -> Void) {
        request(url: url, method: .get, parameters: parameters, headers: headers, callbackForString: callback)
    }
    
    // MARK: - Mappable Object
    static public func post<T: Mappable>(url: String, parameters: Parameters? = nil, headers: HTTPHeaders? = nil, callbackForObject callback: @escaping (RSServiceResponse<T>) -> Void) {
        request(url: url, method: .post, parameters: parameters, headers: headers, callbackForObject: callback)
    }
    
    static public func put<T: Mappable>(url: String, parameters: Parameters? = nil, headers: HTTPHeaders? = nil, callbackForObject callback: @escaping (RSServiceResponse<T>) -> Void) {
        request(url: url, method: .put, parameters: parameters, headers: headers, callbackForObject: callback)
    }
    
    static public func get<T: Mappable>(url: String, parameters: Parameters? = nil, headers: HTTPHeaders? = nil, callbackForObject callback: @escaping (RSServiceResponse<T>) -> Void) {
        request(url: url, method: .get, parameters: parameters, headers: headers, callbackForObject: callback)
    }
    
    // MARK: - Mappable Array
    static public func post<T: Mappable>(url: String, parameters: Parameters? = nil, headers: HTTPHeaders? = nil, callbackForObjectArray callback: @escaping (RSServiceResponse<[T]>) -> Void) {
        request(url: url, method: .post, parameters: parameters, headers: headers, callbackForObjectArray: callback)
    }
    
    static public func put<T: Mappable>(url: String, parameters: Parameters? = nil, headers: HTTPHeaders? = nil, callbackForObjectArray callback: @escaping (RSServiceResponse<[T]>) -> Void) {
        request(url: url, method: .put, parameters: parameters, headers: headers, callbackForObjectArray: callback)
    }
    
    static public func get<T: Mappable>(url: String, parameters: Parameters? = nil, headers: HTTPHeaders? = nil, callbackForObjectArray callback: @escaping (RSServiceResponse<[T]>) -> Void) {
        request(url: url, method: .get, parameters: parameters, headers: headers, callbackForObjectArray: callback)
    }
    
}

// MARK: - Service Response Helper
extension RSServiceHelper {
    
    static public func responseForString(response: DataResponse<String>, callback: @escaping (RSServiceResponse<String>) -> Void) {
        if response.result.isSuccess {
            callback(RSServiceResponse(withData: response.result.value ?? "", statusCode: response.response?.statusCode))
        }
        else {
            var errorMessage: String? = nil
            if let data = response.data {
                if let message = String(data: data, encoding: .utf8) {
                    errorMessage = message
                }
            }
            callback(RSServiceResponse(withError: RSServiceError.requestFailure, statusCode: response.response?.statusCode, errorMessage: errorMessage))
        }
    }
    
    static public func responseForObject<T: Mappable>(response: DataResponse<Any>, callback: @escaping (RSServiceResponse<T>) -> Void) {
        if response.result.isSuccess {
            if let obj = Mapper<T>().map(JSONObject: response.result.value) {
                callback(RSServiceResponse(withData: obj, statusCode: response.response?.statusCode))
            }
            else {
                callback(RSServiceResponse(withError: RSServiceError.responseObjectError, statusCode: response.response?.statusCode))
            }
        }
        else {
            var errorMessage: String? = nil
            if let data = response.data {
                if let message = String(data: data, encoding: .utf8) {
                    errorMessage = message
                }
            }
            callback(RSServiceResponse(withError: RSServiceError.requestFailure, statusCode: response.response?.statusCode, errorMessage: errorMessage))
        }
    }
    
    static public func responseForArray<T: Mappable>(response: DataResponse<Any>, callback: @escaping (RSServiceResponse<[T]>) -> Void) {
        if response.result.isSuccess {
            if let obj = Mapper<T>().mapArray(JSONObject: response.result.value) {
                callback(RSServiceResponse(withData: obj, statusCode: response.response?.statusCode))
            }
            else {
                callback(RSServiceResponse(withError: RSServiceError.responseObjectError, statusCode: response.response?.statusCode))
            }
        }
        else {
            var errorMessage: String? = nil
            if let data = response.data {
                if let message = String(data: data, encoding: .utf8) {
                    errorMessage = message
                }
            }
            callback(RSServiceResponse(withError: RSServiceError.requestFailure, statusCode: response.response?.statusCode, errorMessage: errorMessage))
        }
    }
    
}

// MARK: - Request Helper Default
extension RSServiceHelper {
    
    fileprivate static func request(url: String, method: HTTPMethod, parameters: Parameters?, headers: HTTPHeaders?, callbackForString callback: @escaping (RSServiceResponse<String>) -> Void) {
        RSServiceHelper.restManager().request(url, method: method, parameters: parameters, encoding: method == .get ? URLEncoding.default : JSONEncoding.default, headers: headers)
            .validate(statusCode: [RSHttpStatusCode.success.code, RSHttpStatusCode.accepted.code, RSHttpStatusCode.created.code])
            .responseString(completionHandler: { (response) in
                responseForString(response: response, callback: callback)
            })
    }
    
    fileprivate static func request<T: Mappable>(url: String, method: HTTPMethod, parameters: Parameters?, headers: HTTPHeaders?, callbackForObject callback: @escaping (RSServiceResponse<T>) -> Void) {
        RSServiceHelper.restManager().request(url, method: method, parameters: parameters, encoding: method == .get ? URLEncoding.default : JSONEncoding.default, headers: headers)
            .validate(statusCode: [RSHttpStatusCode.success.code, RSHttpStatusCode.accepted.code, RSHttpStatusCode.created.code])
            .responseJSON(completionHandler: { (response) in
                responseForObject(response: response, callback: callback)
            })
    }
    
    fileprivate static func request<T: Mappable>(url: String, method: HTTPMethod, parameters: Parameters?, headers: HTTPHeaders?, callbackForObjectArray callback: @escaping (RSServiceResponse<[T]>) -> Void) {
        RSServiceHelper.restManager().request(url, method: method, parameters: parameters, encoding: method == .get ? URLEncoding.default : JSONEncoding.default, headers: headers)
            .validate(statusCode: [RSHttpStatusCode.success.code, RSHttpStatusCode.accepted.code, RSHttpStatusCode.created.code])
            .responseJSON(completionHandler: { (response) in
                responseForArray(response: response, callback: callback)
            })
    }
    
}






























