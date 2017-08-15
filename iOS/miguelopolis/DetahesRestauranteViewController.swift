//
//  DetahesRestauranteViewController.swift
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

class DetalhesRestauranteViewController: UIViewController{
    
    @IBOutlet var viewController: UIView!
    
    lazy var restauranteService: RestauranteRestService = RestauranteService()
    var restauranteMangaer = RestauranteManager.shared
    var restaurante: Restaurante = Restaurante()
    
    @IBOutlet weak var labelNomeRestaurante: UILabel!
    @IBOutlet weak var labelDescricaoRestaurante: UILabel!
    @IBOutlet weak var imageEstabelecimento: UIImageView!
    @IBOutlet weak var labelEndereco: UILabel!
    @IBOutlet weak var labelTelefone: UILabel!
    @IBOutlet weak var labelWhatsApp: UILabel!

    override func viewDidLoad() {
        super.viewDidLoad()
        
        let endereco = "Endereço: "
        let telefone = "Telefone: (16) "
        let whatsApp = "WhatsApp: (16) "
        
        
        labelNomeRestaurante.text = restaurante.nome
        labelDescricaoRestaurante.text = restaurante.descricao
        labelEndereco.text = "\(endereco) \(restaurante.endereco!)"
        labelTelefone.text = "\(telefone) \(restaurante.telefone!)"
        labelWhatsApp.text = "\(whatsApp) \(restaurante.whatsapp!)"
        
        let url = URL(string: restaurante.imagemEstabelecimento!)
        imageEstabelecimento.kf.setImage(with: url, placeholder: #imageLiteral(resourceName: "imageplaceholder"), options: [.transition(.fade(0.5))])
        
    }

    
    @IBAction func btnCall(_ sender: Any) {
        
        let descricao = restaurante.telefone!
        let telefone = descricao.replacingOccurrences(of: " ", with: "")
        
        if let url = URL(string: "tel:\(telefone)"), UIApplication.shared.canOpenURL(url) {
            
            if #available(iOS 10, *) {
                UIApplication.shared.open(url)
            } else {
                UIApplication.shared.openURL(url)
            }
        }

    }
    
}
