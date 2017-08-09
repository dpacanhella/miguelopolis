//
//  ControllerIndentifiers.swift
//  starter-kit
//  miguelopolis
//
//  Created by Marcos Vinícius Fernandes on 7/5/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import UIKit
import RSStarterKit

enum ControllerIdentifier: String, RSCustomIdentifier {
    
    case tipoAnunciosViewController = "TipoAnunciosViewController"
    case farmaciasViewController = "FarmaciasViewController"
    case onibusViewController = "OnibusViewController"
    case baresViewController = "BaresViewController"
    case slideMenuViewController = "SlideMenuViewController"
    case lanchonetesViewController = "LanchonetesViewController"
    case lojasViewController = "LojasViewController"
    case utilitariosViewController = "UtilitariosViewController"
    
}

extension ControllerIdentifier {
    
    func getViewController(on storyboard: UIStoryboard) -> UIViewController {
        return storyboard.instantiateViewControllerWithIdentifier(self)
    }
    
}

