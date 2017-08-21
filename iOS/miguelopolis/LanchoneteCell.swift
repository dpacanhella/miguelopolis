//
//  LanchoneteCell.swift
//  miguelopolis
//
//  Created by infra on 04/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import UIKit
import Kingfisher

class LanchoneteCell: UICollectionViewCell {

    
    @IBOutlet weak var labelNome: UILabel!
    @IBOutlet weak var imageLogo: UIImageView!
    @IBOutlet weak var lableTelefone: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib();
    }
    
    override var isSelected: Bool {
        didSet {
            self.alpha = self.isSelected ? 0.5 : 1.0
        }
        
    }
    
    func setLanchonetes(lanchonete: Lanchonete) -> Void {
        labelNome.text = lanchonete.nome
        lableTelefone.text = String(format:"(16) %@",lanchonete.whatsapp!)
        self.imageLogo.layer.masksToBounds = true
        
        let url = URL(string: lanchonete.imagemLogo!)
        
        imageLogo.kf.setImage(with: url, placeholder: nil, options: [.transition(.fade(0.5))])
        
    }
}

