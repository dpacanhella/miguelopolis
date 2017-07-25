//
//  ViewController.swift
//  miguelopolis
//
//  Created by Marcos Vinícius Fernandes on 7/5/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import UIKit
import SlideMenuControllerSwift

class ContainerViewController: SlideMenuController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    override func awakeFromNib() {
        
        if let controller = self.storyboard?.instantiateViewControllerWithIdentifier(ControllerIdentifier.slideMenuViewController) {
            self.leftViewController = controller
        }
        
        replaceMainControllerWithIdentifier(identifier: ControllerIdentifier.utilitariosViewController)
        
        
        SlideMenuOptions.leftViewWidth = 250.0
        SlideMenuOptions.contentViewScale = 1
        super.awakeFromNib()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

}


extension SlideMenuController {
    
    func replaceMainControllerWithIdentifier(identifier: ControllerIdentifier) {
        
        if let controller = self.storyboard?.instantiateViewControllerWithIdentifier(identifier) {
            controller.insertNavigationBarButtonMenu()
            
            let nvc = UINavigationController(rootViewController: controller)
            self.changeMainViewController(nvc, close: true)
            
            addLeftGestures()
        }
        
    }
}

protocol SlideMenuButton {
    
    func insertNavigationBarButtonMenu()
    
}

extension UIViewController: SlideMenuButton {
    
    func insertNavigationBarButtonMenu() {
        let menuButton = UIBarButtonItem(image: ImageIdentifier.iconLeftMenu.getImage(), style: .plain, target: self, action: #selector(toggleMenu))
        navigationItem.leftBarButtonItem = menuButton
    }
    
    func toggleMenu() {
        slideMenuController()?.toggleLeft()
    }
    
}

