//
//  PromocaoCell.swift
//  miguelopolis
//
//  Created by infra on 10/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import UIKit
import Kingfisher

class PromocaoCell: UITableViewCell {


    @IBOutlet weak var imageProduto: UIImageView!
    @IBOutlet weak var nomeProduto: UILabel!
    @IBOutlet weak var descricaoProduto: UILabel!
    @IBOutlet weak var precoProduto: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib();
    }
    
    override var isSelected: Bool {
        didSet {
            self.alpha = self.isSelected ? 0.5 : 1.0
        }
        
    }
    
    func setPromocoes(promocao: Promocao) -> Void {
        nomeProduto.text =  promocao.nomeProduto
        descricaoProduto.text = promocao.precoInicial
        precoProduto.text = promocao.precoFinal
        imageProduto.roundCorners([.topLeft, .topRight], radius: 5)
        
        let url = URL(string: promocao.image64!)
        imageProduto.kf.setImage(with: url, placeholder: nil, options: [.transition(.fade(0.5))])
        
    }
}

