//
//  DetalhesViewController.swift
//  miguelopolis
//
//  Created by infra on 10/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import UIKit
import SVProgressHUD
import RSWidgets
import Kingfisher
import RSStarterKit

class DetalhesViewController: UIViewController{

    @IBOutlet var viewController: UIView!
    
    lazy var farmaciaService: FarmaciaRestService = FarmaciaService()
    
    var farmaciaManager = FarmaciaManager.shared
    
    @IBOutlet weak var labelRazao: UILabel!
    @IBOutlet weak var labelProprietario: UILabel!
    @IBOutlet weak var imageEstabelecimento: UIImageView!
    @IBOutlet weak var labelEndereco: UILabel!
    @IBOutlet weak var labelTelefone: UILabel!
    @IBOutlet weak var labelWhats: UILabel!
    @IBOutlet weak var btnTelefone: UIButton!
    
    var farmacia: Farmacia = Farmacia()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        let endereco = "Endereço: "
        let telefone = "Telefone: (16) "
        let whatsApp = "WhatsApp: (16) "
        

        labelRazao.text = farmacia.razao
        labelProprietario.text = farmacia.nomeProprietario
        labelEndereco.text = "\(endereco) \(farmacia.endereco!)"
        labelTelefone.text = "\(telefone) \(farmacia.telefone!)"
        labelWhats.text = "\(whatsApp) \(farmacia.whatsApp!)"
        
        btnTelefone.n
        
        let url = URL(string: farmacia.imagem!)
        imageEstabelecimento.kf.setImage(with: url, placeholder: #imageLiteral(resourceName: "imageplaceholder"), options: [.transition(.fade(0.5))])
    
    }
}
