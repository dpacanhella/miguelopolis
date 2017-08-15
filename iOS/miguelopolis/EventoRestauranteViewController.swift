//
//  EventoRestauranteViewController.swift
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

class EventoRestauranteViewController: UIViewController{
    
    @IBOutlet var viewController: UIView!
    lazy var restauranteService: RestauranteRestService = RestauranteService()
    var restauranteMangaer = RestauranteManager.shared
    var restaurante: Restaurante = Restaurante()
    
    @IBOutlet weak var labelDescricao1: UILabel!
    @IBOutlet weak var labelDescricao2: UILabel!
    @IBOutlet weak var image1: UIImageView!
    @IBOutlet weak var labelDescricao3: UILabel!
    @IBOutlet weak var image2: UIImageView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        labelDescricao1.text = restaurante.descricao2
        labelDescricao2.text = restaurante.descricao3
        labelDescricao3.text = restaurante.descricao5
        
        let url1 = URL(string: restaurante.imagem1!)
        image1.kf.setImage(with: url1, placeholder: #imageLiteral(resourceName: "imageplaceholder"), options: [.transition(.fade(0.5))])
        
        let url2 = URL(string: restaurante.imagem2!)
        image2.kf.setImage(with: url2, placeholder: #imageLiteral(resourceName: "imageplaceholder"), options: [.transition(.fade(0.5))])
        
    }
}
