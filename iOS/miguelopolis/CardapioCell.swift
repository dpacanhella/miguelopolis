//
//  CardapioCell.swift
//  miguelopolis
//
//  Created by infra on 15/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//


import Foundation
import UIKit
import Kingfisher

class CardapioCell: UITableViewCell {

    
    @IBOutlet weak var imgCardapio: UIImageView!
    
    
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
        
        self.imgCardapio.layer.masksToBounds = true
        
        imgCardapio.kf.setImage(with: url, placeholder: #imageLiteral(resourceName: "imageplaceholder"), options: [.transition(.fade(0.5))])
        
    }
}
