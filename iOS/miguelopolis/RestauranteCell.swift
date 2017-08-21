//
//  RestauranteCell.swift
//  miguelopolis
//
//  Created by infra on 03/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import UIKit
import Kingfisher

class RestauranteCell: UICollectionViewCell {
  
    
    @IBOutlet weak var imageLogo: UIImageView!
    @IBOutlet weak var labelNome: UILabel!
    @IBOutlet weak var lableTelefone: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib();
    }
    
    override var isSelected: Bool {
        didSet {
            self.alpha = self.isSelected ? 0.5 : 1.0
        }
    
    }
    
    func setRestaurantes(restaurante: Restaurante) -> Void {
        labelNome.text = restaurante.nome
        lableTelefone.text = String(format:"(16) %@",restaurante.telefone!)
        self.imageLogo.layer.masksToBounds = true

        let url = URL(string: restaurante.imagemLogo!)

        imageLogo.kf.setImage(with: url, placeholder: #imageLiteral(resourceName: "imageplaceholder"), options: [.transition(.fade(0.5))])

    }
}
