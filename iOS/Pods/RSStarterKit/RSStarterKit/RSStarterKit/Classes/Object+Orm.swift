//
//  Object+Orm.swift
//  Pods
//
//  Created by Marcus Costa on 11/10/16.
//
//

import Foundation
import RealmSwift

public protocol RSOrm {
    
    associatedtype MyType: Object
    
    // Instance
    func save()
    func delete()
    func update()
    
    // Static
    static func save(elements: [MyType])
    static func update(elements: [MyType])
    static func deleteAll()
    static func delete(elements: [MyType])
    static func count() -> Int
    static func count(withPredicate predicate: NSPredicate) -> Int
    static func findFirst() -> MyType?
    static func findAll() -> Results<MyType>
    static func find(withPredicate predicate: NSPredicate) -> Results<MyType>
    static func findFirst(withPredicate predicate: NSPredicate) -> MyType?
    static func find(withId id: Int) -> MyType?
    static func write(_ write: (() -> Void)) throws
    static func update(write: (() -> Void)) throws
}

// MARK: - Instance Methods
extension RSOrm where Self: Object {
    
    public var dbHelper: RSDbHelper<MyType> {
        return RSDbHelper<MyType>()
    }
    
    public var instance: MyType {
        return (self as! MyType)
    }
    
    public func save() {
        dbHelper.save(instance)
    }
    
    public func delete() {
        dbHelper.delete(instance)
    }
    
    public func update() {
        dbHelper.update(instance)
    }
    
    public static var dbHelper: RSDbHelper<MyType> {
        return RSDbHelper<MyType>()
    }
    
    public static func save(elements: [MyType]) {
        dbHelper.save(elements: elements)
    }
    
    public static func update(elements: [MyType]) {
        dbHelper.update(elements: elements)
    }
    
    public static func deleteAll() {
        dbHelper.delete(elements: dbHelper.findAll().toArray(MyType))
    }
    
    public static func delete(elements: [MyType]) {
        dbHelper.delete(elements: elements)
    }
    
    public static func count() -> Int {
        return dbHelper.count()
    }
    
    public static func count(withPredicate predicate: NSPredicate) -> Int {
        return dbHelper.count(withPredicate: predicate)
    }
    
    public static func findFirst() -> MyType? {
        return dbHelper.findFirst()
    }
    
    public static func findAll() -> Results<MyType> {
        return dbHelper.findAll()
    }
    
    public static func find(withPredicate predicate: NSPredicate) -> Results<MyType> {
        return dbHelper.find(withPredicate: predicate)
    }
    
    public static func findFirst(withPredicate predicate: NSPredicate) -> MyType? {
        return dbHelper.findFirst(withPredicate: predicate)
    }
    
    public static func find(withId id: Int) -> MyType? {
        return dbHelper.find(withId: id)
    }
    
    public static func write(_ write: (() -> Void)) throws {
        try dbHelper.write {
            write()
        }
    }
    
    public static func update(write: (() -> Void)) throws {
        try dbHelper.write {
            write()
        }
    }
    
}






