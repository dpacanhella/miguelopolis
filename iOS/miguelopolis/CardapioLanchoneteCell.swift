//
//  CardapioLanchoneteCell.swift
//  miguelopolis
//
//  Created by infra on 16/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import UIKit
import Kingfisher

class CardapioCell: UITableViewCell {
    
    
    override func awakeFromNib() {
        super.awakeFromNib();
    }
    
    override var isSelected: Bool {
        didSet {
            self.alpha = self.isSelected ? 0.5 : 1.0
        }
        
    }
    
    func setCardapios(cardapio: Cardapios) -> Void {
        let url = URL(string: cardapio.imagem!)
        
        imgCardapio.kf.setImage(with: url, placeholder: #imageLiteral(resourceName: "imageplaceholder"), options: [.transition(.fade(0.5))])
        
    }
}
