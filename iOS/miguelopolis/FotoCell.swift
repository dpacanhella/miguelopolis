//
//  FotoCell.swift
//  miguelopolis
//
//  Created by infra on 16/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import UIKit
import Kingfisher

class FotoCell: UITableViewCell {
    
    
    @IBOutlet weak var imgFoto: UIImageView!
    @IBOutlet weak var labelDescricao: UILabel!
    
    
    override func awakeFromNib() {
        super.awakeFromNib();
    }
    
    override var isSelected: Bool {
        didSet {
            self.alpha = self.isSelected ? 0.5 : 1.0
        }
        
    }
    
    func setFotos(imagem: ImagemLoja) -> Void {
        labelDescricao.text = imagem.descricao!
        
        imgFoto.roundCorners([.topLeft, .topRight], radius: 8)
        
        let url = URL(string: imagem.imagem!)
        
        imgFoto.kf.setImage(with: url, placeholder: #imageLiteral(resourceName: "imageplaceholder"), options: [.transition(.fade(0.5))])
        
    }
}
