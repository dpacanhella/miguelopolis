//
//  LojaCell.swift
//  miguelopolis
//
//  Created by infra on 04/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import UIKit
import Kingfisher

class UtilitarioCell: UICollectionViewCell {
    
    
    @IBOutlet weak var imageLogo: UIImageView!
    @IBOutlet weak var labelNome: UILabel!
    @IBOutlet weak var labelDescricao: UILabel!
    
    
    override func awakeFromNib() {
        super.awakeFromNib();
    }
    
    override var isSelected: Bool {
        didSet {
            self.alpha = self.isSelected ? 0.5 : 1.0
        }
        
    }

    func setUtilitarios(utilitarios: Utilitarios) -> Void {
        labelNome.text =  utilitarios.nome
        labelDescricao.text = utilitarios.descricao
        imageLogo.roundCorners([.topLeft, .topRight], radius: 5)
        
        let url = URL(string: utilitarios.imagem!)
        imageLogo.kf.setImage(with: url, placeholder: nil, options: [.transition(.fade(0.5))])
        
    }
}

