//
//  ImagesIdentifiers.swift
//  starter-kit
//  miguelopolis
//
//  Created by Marcos Vinícius Fernandes on 7/5/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import UIKit
import RSStarterKit

enum ImageIdentifier: String, RSCustomIdentifier {
    
    case defaultImage = "default_image"
    
    /* Menu */
    
    case iconLeftMenu = "menu_button"
    case menuUtilitarios = "menu_utilitarios"
    case menuFarmacias = "menu_farmacias"
    case menuOnibus = "menu_onibus"
    case menuBares = "menu_bares"
    case menuLanchonetes = "menu_restaurantes"
    case menuLojas = "menu_lojas"
    
}

extension ImageIdentifier {
    
    func getImage() -> UIImage {
        return UIImage(withImageIdentifier: self)
    }
    
}
