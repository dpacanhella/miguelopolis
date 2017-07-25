//
//  MenuItem.swift
//  miguelopolis
//
//  Created by Marcos Vinícius Fernandes on 7/5/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import UIKit

protocol MenuItemProtocol {
    func title() -> String
    func controllerIdentifier() -> ControllerIdentifier
    func image() -> UIImage
}

enum MenuItem: Int, MenuItemProtocol {
    
    case utilitarios = 0, farmacias, onibus, bares
    
    static let count = 4
    
    func title() -> String {
        switch self {
        case .utilitarios:
            return String(withCustomIdentifier: StringIdentifier.menuUtilitarios)
        case .farmacias:
            return String(withCustomIdentifier: StringIdentifier.menuFarmacias)
        case .onibus:
            return String(withCustomIdentifier: StringIdentifier.menuOnibus)
        case .bares:
            return String(withCustomIdentifier: StringIdentifier.menuBares)
        }
    }
    
    func image() -> UIImage {
        var image: UIImage
        
        switch self {
        case .utilitarios:
            image = ImageIdentifier.menuUtilitarios.getImage()
        case .farmacias:
            image = ImageIdentifier.menuFarmacias.getImage()
        case .onibus:
            image = ImageIdentifier.menuOnibus.getImage()
        case .bares:
            image = ImageIdentifier.menuBares.getImage()
        }
        
        image = image.withRenderingMode(UIImageRenderingMode.alwaysTemplate)
        return image
    }
    
    func controllerIdentifier() -> ControllerIdentifier {
        switch self {
        case .utilitarios:
            return .utilitariosViewController
        case .farmacias:
            return .farmaciasViewController
        case .onibus:
            return .onibusViewController
        case .bares:
            return .baresViewController
        }
    }
    
}
