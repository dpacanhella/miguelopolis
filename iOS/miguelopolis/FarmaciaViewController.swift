//
//  FarmaciaViewController.swift
//  miguelopolis
//
//  Created by Marcos Vinícius Fernandes on 7/5/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import UIKit
import SVProgressHUD

class FarmaciaViewController: UIViewController{
    
    @IBOutlet weak var tableView: UITableView!
    
    lazy var farmaciaService: FarmaciaRestService = FarmaciaService()
    
    var farmaciaManager = FarmaciaManager.shared
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        setupCollectionView()
        
        refreshData()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        refreshData()
        
    }
    
    func setupCollectionView() {
        tableView.register(UINib(nibName: "FarmaciaCell", bundle: nil), forCellReuseIdentifier: "farmacia_cell")
        self.tableView.delegate = self;
        self.tableView.dataSource = self;
    }
    
    func refreshData(){
        if farmaciaManager.farmacias.isEmpty{
            
            SVProgressHUD.show()
            farmaciaService.list { (response) in
                SVProgressHUD.dismiss()
                if response.isSuccess{
                    self.farmaciaManager.farmacias = response.data!
                    self.tableView.reloadData()
                } else {
                    
                }
            }
        }
    }
    
}

extension FarmaciaViewController: UITableViewDelegate{
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
    }
}

extension FarmaciaViewController: UITableViewDataSource{
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {

        return farmaciaManager.farmacias.count
    
    }
    
    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "farmacia_cell") as! FarmaciaCell
        
        let farmacia = self.farmaciaManager.farmacias[indexPath.row]

        cell.setFarmacia(farmacia: farmacia)
        
        return cell
    }
    
}
