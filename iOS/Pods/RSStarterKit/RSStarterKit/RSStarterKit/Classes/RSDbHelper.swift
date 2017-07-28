//
//  RSDbHelper.swift
//  gestaogiro
//
//  Created by Andre M. Della Torre on 6/27/16.
//  Copyright © 2016 redspark. All rights reserved.
//

import Foundation
import RealmSwift

open class RSDbHelper<T> where T: Object {
    
    public init() {
        
    }
    
    open var conn: Realm {
        return RSDbConnection.connection
    }
    
    open func save(_ element: T) {
        
        let lastId: Int = conn.objects(T.self).max(ofProperty: "id") ?? -1
        
        
        try! conn.write {
            element.setValue(lastId + 1, forKey: "id")
            conn.add(element)
        }
    }

    @available(*, deprecated, message: "switf 3.0 sintax: Use save(elements:)")
    open func save(_ elements: [T]) {
        save(elements: elements)
    }
    
    open func save(elements: [T]) {
        
        var lastId: Int = conn.objects(T.self).max(ofProperty: "id") ?? -1
        
        try! conn.write {
            elements.forEach({ (element) in
                lastId += 1
                element.setValue(lastId, forKey: "id")
                conn.add(element)
            })
        }
    }
    
    open func update(_ element: T) {
        try! conn.write {
            conn.add(element, update: true)
        }
    }
    
    @available(*, deprecated, message: "switf 3.0 sintax: Use update(elements:)")
    open func update(_ elements: [T]) {
        update(elements: elements)
    }
    
    open func update(elements: [T]) {
        try! conn.write {
            elements.forEach({ (element) in
                conn.add(element, update: true)
            })
        }
    }
    
    open func deleteAll() {
        try! conn.write {
            conn.deleteAll()
        }
    }
    
    open func delete(_ element: T) {
        try! conn.write {
            conn.delete(element)
        }
    }
    
    @available(*, deprecated, message: "switf 3.0 sintax: Use delete(elements:)")
    open func delete(_ elements: [T]) {
        delete(elements: elements)
    }
    
    open func delete(elements: [T]) {
        try! conn.write {
            elements.forEach({ (element) in
                conn.delete(element)
            })
        }
    }
    
    open func write(_ block: (() -> Void)) throws {
        try conn.write {
            block()
        }
    }
    
    open func count() -> Int {
        return findAll().count
    }
    
    @available(*, deprecated, message: "switf 3.0 sintax: Use count(predicate:)")
    open func countWithPredicate(_ predicate: NSPredicate) -> Int {
        return count(withPredicate: predicate)
    }
    
    open func count(withPredicate predicate: NSPredicate) -> Int {
        return find(withPredicate: predicate).count
    }
    
    open func findFirst() -> T? {
        return conn.objects(T.self).first
    }
    
    open func findAll() -> Results<T> {
        return conn.objects(T.self)
    }
    
    @available(*, deprecated, message: "switf 3.0 sintax: Use find(predicate:)")
    open func findWithPredicate(_ predicate: NSPredicate) -> Results<T> {
        return find(withPredicate: predicate)
    }
    
    open func find(withPredicate predicate: NSPredicate) -> Results<T> {
        return conn.objects(T.self).filter(predicate)
    }
    
    @available(*, deprecated, message: "switf 3.0 sintax: Use findFirst(predicate:)")
    open func findFirstWithPredicate(_ predicate: NSPredicate) -> T? {
        return findFirst(withPredicate: predicate)
    }
    
    open func findFirst(withPredicate predicate: NSPredicate) -> T? {
        return conn.objects(T.self).filter(predicate).first
    }
    
    @available(*, deprecated, message: "switf 3.0 sintax: Use find(id:)")
    open func findWithId(_ id: Int) -> T? {
        return find(withId: id)
    }
    
    open func find(withId id: Int) -> T? {
        let query = NSPredicate(format: "id = \(id)")
        return findWithPredicate(query).first
    }
    
}
