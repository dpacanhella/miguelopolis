//
//  FarmaciaCell.swift
//  miguelopolis
//
//  Created by Marcos Vinícius Fernandes on 7/5/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import UIKit

class FarmaciaCell: UITableViewCell {
    
    @IBOutlet weak var labelNome: UILabel!
    @IBOutlet weak var labelProprietario: UILabel!
    @IBOutlet weak var labelTelefone: UILabel!
    @IBOutlet weak var labelWhatsapp: UILabel!
    @IBOutlet weak var labelFuncionamento: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib();
    }
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    func setFarmacia(farmacia: Farmacia) -> Void {
        
        labelFuncionamento.isHidden = true
        self.contentView.backgroundColor = UIColor.white
        if farmacia.plantao!{
            labelFuncionamento.isHidden = false
            labelNome.textColor = UIColor.red
        } else {
            self.contentView.backgroundColor = UIColor.lightGray
            labelNome.textColor = UIColor.black
        }
        
        
        labelNome.text = farmacia.razao
        labelProprietario.text = farmacia.nomeProprietario
        labelTelefone.text = String(format:"Telefone: (16) %@",farmacia.telefone!)
        labelWhatsapp.text = String(format:"WhatsApp: (16) %@",farmacia.whatsApp!)
    }
}
