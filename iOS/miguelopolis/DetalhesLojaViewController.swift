//
//  DetalhesLojaViewController.swift
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

class DetalhesLojaViewController: UIViewController{
    
    var loja: Loja = Loja()

    @IBOutlet weak var labelNome: UILabel!
    @IBOutlet weak var labelDescricao: UILabel!
    @IBOutlet weak var imagemLoja: UIImageView!
    @IBOutlet weak var labelEndereco: UILabel!
    @IBOutlet weak var labelWhatsApp: UILabel!
    @IBOutlet weak var labelTelefone: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let endereco = "Endereço: "
        let telefone = "Telefone: (16) "
        let whatsApp = "WhatsApp: (16) "
        
        labelNome.text = loja.nome
        labelDescricao.text = loja.descricao
        labelEndereco.text = "\(endereco) \(loja.endereco!)"
        labelTelefone.text = "\(telefone) \(loja.telefone!)"
        labelWhatsApp.text = "\(whatsApp) \(loja.celular!)"
        
        let url = URL(string: loja.imagemEstabelecimento!)
        imagemLoja.kf.setImage(with: url, placeholder: #imageLiteral(resourceName: "imageplaceholder"), options: [.transition(.fade(0.5))])
    }

    
    @IBAction func btnCall(_ sender: Any) {
        let descricao = loja.telefone!
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
