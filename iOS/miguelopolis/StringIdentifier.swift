//
//  StringIdentifiers.swift
//  starter-kit
//  miguelopolis
//
//  Created by Marcos Vinícius Fernandes on 7/5/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import RSStarterKit

enum StringIdentifier: String, RSCustomIdentifier {
    
    /* Menu */
    
    case menuUtilitarios = "menu_utilitarios"
    case menuFarmacias = "menu_farmacias"
    case menuOnibus = "menu_onibus"
    case menuBares = "menu_bares"
    case menuLanchonetes = "menu_restaurantes"
    case menuLojas = "menu_lojas"

}

extension StringIdentifier {
    
    func getString() -> String {
        return String(withCustomIdentifier: self)
    }
    
}
