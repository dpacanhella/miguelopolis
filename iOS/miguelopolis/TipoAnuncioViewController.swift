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

class TipoAnuncioViewController: UIViewController{
    
    @IBOutlet weak var tableView: UITableView!
    
    lazy var tipoAnuncioService: TipoAnuncioRestService = TipoAnuncioService()
    
    var tipoAnuncioManager = TipoAnuncioManager.shared
    
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
        tableView.register(UINib(nibName: "TipoAnuncioCell", bundle: nil), forCellReuseIdentifier: "tipoAnuncio_cell")
        self.tableView.delegate = self;
        self.tableView.dataSource = self;
    }
    
    func refreshData(){
        if tipoAnuncioManager.tiposAnuncios.isEmpty{
            
            SVProgressHUD.show()
            tipoAnuncioService.list { (response) in
                SVProgressHUD.dismiss()
                if response.isSuccess{
                    self.tipoAnuncioManager.tiposAnuncios = response.data!
                    self.tableView.reloadData()
                } else {
                    
                }
            }
        }
    }
    
}

extension TipoAnuncioViewController: UITableViewDelegate{
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
    }
}

extension TipoAnuncioViewController: UITableViewDataSource{
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        return tipoAnuncioManager.tiposAnuncios.count
        
    }
    
    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "tipoAnuncio_cell") as! TipoAnuncioCell
        
        let tipoAnuncio = self.tipoAnuncioManager.tiposAnuncios[indexPath.row]
        
        cell.setTipoAnuncios(tipoAnuncio: tipoAnuncio)
        
        return cell
    }
    
}
