//
//  DetalhesLanchoneteViewController.swift
//  miguelopolis
//
//  Created by infra on 16/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import UIKit
import SVProgressHUD
import RSWidgets
import Kingfisher
import RSStarterKit

class DetalhesLanchoneteViewController: UIViewController{

    
    lazy var lanchoneteService: LanchoneteRestService = LanchoneteService()
    var lanchoneteManager = LanchoneteManager.shared
    var lanchonete: Lanchonete = Lanchonete()
    
    @IBOutlet weak var labelNome: UILabel!
    @IBOutlet weak var labelProprietario: UILabel!
    @IBOutlet weak var imagemEstabelecimento: UIImageView!
    @IBOutlet weak var labelEndereco: UILabel!
    @IBOutlet weak var labelWhatsApp: UILabel!
    @IBOutlet weak var labelTelefone: UILabel!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let endereco = "Endereço: "
        let telefone = "Telefone: (16) "
        let whatsApp = "WhatsApp: (16) "
        
        labelNome.text = lanchonete.nome
        labelProprietario.text = lanchonete.nomeProprietario
        labelEndereco.text = "\(endereco) \(lanchonete.endereco!)"
        labelTelefone.text = "\(telefone) \(lanchonete.telefone!)"
        labelWhatsApp.text = "\(whatsApp) \(lanchonete.whatsapp!)"
        
        let url = URL(string: lanchonete.imagemEstabelecimento!)
        imagemEstabelecimento.kf.setImage(with: url, placeholder: #imageLiteral(resourceName: "imageplaceholder"), options: [.transition(.fade(0.5))])
    }
    
    @IBAction func btnCall(_ sender: Any) {
        
        let descricao = lanchonete.telefone!
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
