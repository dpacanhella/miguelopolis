//
//  CardapioRestauranteViewController.swift
//  miguelopolis
//
//  Created by infra on 15/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import UIKit
import SVProgressHUD
import RSWidgets
import Kingfisher
import RSStarterKit

class CardapioRestauranteViewController: UIViewController{
    
    lazy var restauranteService: RestauranteRestService = RestauranteService()
    var restauranteMangaer = RestauranteManager.shared
    var restaurante: Restaurante = Restaurante()
    
    @IBOutlet weak var labelSemPromocao: UILabel!
    @IBOutlet weak var tableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        print(restaurante.nome!)
        
    }
    
}
