//
//  TipoAnuncioSwift.swift
//  miguelopolis
//
//  Created by infra on 02/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import UIKit

class TipoAnuncioCell: UITableViewCell {
    
    @IBOutlet weak var labelDescricao: UILabel!
    @IBOutlet weak var labelQtdeUtilitario: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib();
    }
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    func setTipoAnuncios(tipoAnuncio: TipoAnuncio) -> Void {
        labelDescricao.text = tipoAnuncio.descricao
        labelQtdeUtilitario.text = "\(String(tipoAnuncio.qtdeUtilitario!))"
        
    }
}
