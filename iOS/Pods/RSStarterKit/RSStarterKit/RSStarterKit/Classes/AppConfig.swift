//
//  AppConfig.swift
//  Pods
//
//  Created by Andre M. Della Torre on 6/27/16.
//
//

import Foundation

public struct AppConfig {
    
    public enum Key: String {
        case ServiceUrl
        case ServiceDebbugerMode
        case DatabaseEncrypt
        case LoginTimeOut
    }
    
    fileprivate enum ConfigMap: String {
        case Login = "Login"
        case LoginTimeOut = "Time Out"
        
        case Database = "database"
        case DatabaseEncypt = "Encrypt"
        
        case Restful = "restful"
        case ProdUrl = "Prod URL"
        case HomologUrl = "Homolog URL"
        case BuildForProduction = "Build For Production"
        case ServiceDebbuger = "Service Debbuger"
    }
    
    static fileprivate var configDict: NSDictionary {
        guard let path = Bundle.main.path(forResource: "AppConfig", ofType: "plist"),
            let config = NSDictionary(contentsOfFile: path) else {
                NSException(name: NSExceptionName(rawValue: "Error"), reason: "Create a .plist file with your config data", userInfo: nil).raise()
                return NSDictionary()
        }
        
        return config
    }
    
    static public func config<T>(withKey key: Key) -> T {
        var finalKey = String()
        var finalConfigDict = NSDictionary()
        
        switch key {
        case .ServiceUrl:
            finalConfigDict = configDict[ConfigMap.Restful.rawValue] as! NSDictionary
            let buildForProduction = finalConfigDict[ConfigMap.BuildForProduction.rawValue] as! Bool
            finalKey = "\(buildForProduction ? "Prod" : "Homolog") URL"
            
            
        case .ServiceDebbugerMode:
            finalConfigDict = configDict[ConfigMap.Restful.rawValue] as! NSDictionary
            finalKey = ConfigMap.ServiceDebbuger.rawValue
            
        case .DatabaseEncrypt:
            finalConfigDict = configDict[ConfigMap.Database.rawValue] as! NSDictionary
            finalKey = ConfigMap.DatabaseEncypt.rawValue
            
        case .LoginTimeOut:
            finalConfigDict = configDict[ConfigMap.Login.rawValue] as! NSDictionary
            finalKey = ConfigMap.LoginTimeOut.rawValue
        }
        
        if finalKey.characters.count <= 0 {
            NSException(name: NSExceptionName(rawValue: "Error"), reason: "Invalid config type, register config at enum value Key and implement the case method", userInfo: nil).raise()
        }
        
        return finalConfigDict[finalKey] as! T
    }
}
