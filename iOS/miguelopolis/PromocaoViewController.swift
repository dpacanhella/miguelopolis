//
//  PromocaoViewController.swift
//  miguelopolis
//
//  Created by infra on 10/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import UIKit
import SVProgressHUD
import RSWidgets
import RSStarterKit

class PromocaoViewController: UIViewController{
    
    @IBOutlet weak var tableView: UITableView!
    
    lazy var promocaoService: PromocoesRestService = PromocoesService()
    
    var promocaoManager = PromocoesManager.shared
    
    var farmacia: Farmacia = Farmacia()
    
    @IBOutlet weak var semPromocao: UILabel!
    
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
        tableView.register(UINib(nibName: "PromocaoCell", bundle: nil), forCellReuseIdentifier: "promocao_cell")
        self.tableView.delegate = self;
        self.tableView.dataSource = self;
    }
    
    func refreshData(){
            SVProgressHUD.show()
            print(farmacia.id!)
            promocaoService.list(id: farmacia.id!) { (response) in
                SVProgressHUD.dismiss()
                if response.isSuccess{
                    self.promocaoManager.promocoes = response.data!
                    self.tableView.reloadData()
                } else {
                    
            }
        }
    }
    
}

extension PromocaoViewController: UITableViewDelegate{
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
    }
    
}

extension PromocaoViewController: UITableViewDataSource{
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        if (promocaoManager.promocoes.count < 1) {
            semPromocao.text = "Não há promoções cadastradas no momento"
        } else {
            semPromocao.text = ""
        }
            
        return promocaoManager.promocoes.count
        
    }
    
    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "promocao_cell") as! PromocaoCell
        
        let promocao = self.promocaoManager.promocoes[indexPath.row]
        
        cell.setPromocoes(promocao: promocao)
        
        return cell
    }
    
    
    
}
