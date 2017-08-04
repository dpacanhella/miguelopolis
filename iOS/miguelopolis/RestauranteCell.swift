//
//  RestauranteCell.swift
//  miguelopolis
//
//  Created by infra on 03/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import UIKit

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
        imageLogo.roundCorners([.topLeft, .topRight], radius: 5)
    
        let url = URL(string: restaurante.imagemLogo!)
        
        DispatchQueue.global().async {
            let data = try? Data(contentsOf: url!)
            DispatchQueue.main.async {
                self.imageLogo.image = UIImage(data: data!)
            }
        }
        
    }


}

extension UIImageView {
    func roundCorners(_ corners:UIRectCorner, radius: CGFloat) {
        let path = UIBezierPath(roundedRect: self.bounds, byRoundingCorners: corners, cornerRadii: CGSize(width: radius, height: radius))
        let mask = CAShapeLayer()
        mask.path = path.cgPath
        self.layer.mask = mask
    }
}
