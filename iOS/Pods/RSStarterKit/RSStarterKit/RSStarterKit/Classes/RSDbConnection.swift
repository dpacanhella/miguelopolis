//
//  DBHelper.swift
//  gestaogiro
//
//  Created by Marcus Costa on 6/8/16.
//  Copyright © 2016 redspark. All rights reserved.
//

import Foundation
import RealmSwift
import KeychainAccess


public struct RSDbConnection {
    
    fileprivate static var encrytionKey: Data {
        let keychain = Keychain(service: "\(Bundle.main.bundleIdentifier ?? String()).database")
        
        let encryptKey = "\(Bundle.main.bundleIdentifier).encrypt.key"
        if let key = keychain[data: encryptKey] {
            return key
        }
        
        let key = generateEncrytionKey()
        keychain[data: encryptKey] = key
        
        return key
    }
    
    fileprivate static func generateEncrytionKey() -> Data {
        var key = Data(count: 64)
        key.withUnsafeMutableBytes { bytes in
            SecRandomCopyBytes(kSecRandomDefault, 64, bytes)
        }
        
        return key
    }
    
    static public var connection: Realm {
        let encrypt: Bool = AppConfig.config(withKey: .DatabaseEncrypt)
        if(encrypt) {
            let config = Realm.Configuration(encryptionKey: RSDbConnection.encrytionKey)
            return try! Realm(configuration: config)
        }
        else {
            return try! Realm()
        }
    }
    
}
