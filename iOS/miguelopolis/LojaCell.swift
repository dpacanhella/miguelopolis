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

class LojaCell: UICollectionViewCell {
    
    
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
    
    func setLojas(loja: Loja) -> Void {
        labelNome.text =  loja.nome
        labelDescricao.text = loja.descricao
        self.imageLogo.layer.masksToBounds = true
        
        let url = URL(string: loja.imagemLogo!)
        
        imageLogo.kf.setImage(with: url, placeholder: nil, options: [.transition(.fade(0.5))])
        
    }
}

