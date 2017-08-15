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
    @IBOutlet weak var labelDescricao: UILabel!
    
    
    override func awakeFromNib() {
        super.awakeFromNib();
    }
    
    override var isSelected: Bool {
        didSet {
            self.alpha = self.isSelected ? 0.5 : 1.0
        }
        
    }
    
    func setCardapios(cardapio: Cardapio) -> Void {
//        nomeProduto.text =  promocao.nomeProduto
//        descricaoProduto.text = promocao.precoInicial
//        precoProduto.text = promocao.precoFinal
//        imageProduto.roundCorners([.topLeft, .topRight], radius: 5)
//        
//        let url = URL(string: promocao.image64!)
//        
//        imageProduto.kf.setImage(with: url, placeholder: #imageLiteral(resourceName: "imageplaceholder"), options: [.transition(.fade(0.5))])
        
    }
}
