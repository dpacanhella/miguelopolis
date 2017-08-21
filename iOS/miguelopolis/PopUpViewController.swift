//
//  PopUpViewController.swift
//  miguelopolis
//
//  Created by infra on 17/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import UIKit

class PopUpViewController: UIViewController {
    
    var utilitario: Utilitarios = Utilitarios()

    @IBOutlet weak var labelNome: UILabel!
    @IBOutlet weak var labelDescricao: UILabel!
    @IBOutlet weak var labelEndereco: UILabel!
    @IBOutlet weak var labelTelefone: UILabel!
    @IBOutlet weak var labelCelular: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.view.backgroundColor = UIColor.darkGray.withAlphaComponent(0.8)
        
        labelNome.text = utilitario.nome!
        labelDescricao.text = utilitario.descricao!
        labelEndereco.text = utilitario.endereco!
        labelTelefone.text = String(format:"(16) %@",utilitario.telefone!)
        labelCelular.text = String(format:"(16) %@",utilitario.celular!)
       
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }

    @IBAction func btnClose(_ sender: Any) {
        self.dismiss(animated: true, completion: nil)
    }
}
