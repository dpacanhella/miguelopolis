//
//  SlideMenuCell.swift
//  miguelopolis
//
//  Created by Marcos Vinícius Fernandes on 7/5/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import UIKit

class SlideMenuCell: UITableViewCell {
    
    private var menuItem: MenuItemProtocol?
    
    @IBOutlet weak var labelMenuItem: UILabel!
    @IBOutlet weak var imageMenuView: UIImageView!
    
    override func awakeFromNib() {
        super.awakeFromNib();
        let bgColorView = UIView()
        bgColorView.backgroundColor = UIColor.migueGrey
        self.selectedBackgroundView = bgColorView
    }
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    func setMenuItem(menuItem: MenuItemProtocol) {
        self.menuItem = menuItem
        labelMenuItem.text = self.menuItem?.title()
        imageMenuView.image = self.menuItem?.image()
    }
}
