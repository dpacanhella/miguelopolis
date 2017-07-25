//
//  SlideMenuViewController.swift
//  miguelopolis
//
//  Created by Marcos Vinícius Fernandes on 7/5/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import UIKit
import RSWidgets
import RSStarterKit

class SlideMenuViewController: UIViewController {
    
    @IBOutlet weak var tableView: UITableView!
    
    static var restoreSelection = true
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.tableView.delegate = self
        self.tableView.dataSource = self
        
        tableView.register(UINib(identifier: NibIdentifier.slideMenuCell, bundle: nil), forCellReuseIdentifier: "slideMenuCell")
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        if SlideMenuViewController.restoreSelection {
            tableView.reloadData()
            SlideMenuViewController.restoreSelection = false
            
            tableView.selectRow(at: IndexPath(row: 0, section: 0), animated: false, scrollPosition: .none)
        }
        
    }
    
}

// MARK - UITableViewDataSource
extension SlideMenuViewController: UITableViewDataSource {
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return MenuItem.count
    }
    
    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "slideMenuCell", for: indexPath) as! (SlideMenuCell)
        
        if let menuItem = MenuItem(rawValue: indexPath.row) {
            cell.setMenuItem(menuItem: menuItem)
        }
        
        return cell
    }
    
}

// MARK: - UITableViewDelegate
extension SlideMenuViewController: UITableViewDelegate {
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if let menuItem = MenuItem(rawValue: indexPath.row) {
            slideMenuController()?.replaceMainControllerWithIdentifier(identifier: menuItem.controllerIdentifier())
        }
    }
    
}
