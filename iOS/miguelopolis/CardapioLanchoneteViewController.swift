//
//  CardapioLanchoneteViewController.swift
//  miguelopolis
//
//  Created by infra on 16/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import UIKit
import SVProgressHUD
import RSWidgets
import Kingfisher
import RSStarterKit

class CardapioLanchoneteViewController: UIViewController{

    lazy var cardapioService: CardapiosLanchoneteRestService = CardapiosLanchoneteService()
    var cardapioManager = CardapiosManager.shared
    var lanchonete: Lanchonete = Lanchonete()
    
    @IBOutlet weak var tableView: UITableView!
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
        tableView.rowHeight = UITableViewAutomaticDimension
        tableView.estimatedRowHeight = 200
        tableView.register(UINib(nibName: "CardapioCell", bundle: nil), forCellReuseIdentifier: "cardapio_cell")
        self.tableView.delegate = self;
        self.tableView.dataSource = self;
    }
    
    func refreshData(){
        SVProgressHUD.show()
        
        print(lanchonete.id!)
        
        cardapioService.list(id: lanchonete.id!) { (response) in
            SVProgressHUD.dismiss()
            if response.isSuccess{
                self.cardapioManager.cardapios = response.data!
                self.tableView.reloadData()
            } else {
                
            }
        }
    }
}

extension CardapioLanchoneteViewController: UITableViewDelegate{
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
    }
}

extension CardapioLanchoneteViewController: UITableViewDataSource{
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        if (cardapioManager.cardapios.count < 1) {
            semPromocao.text = "Não há cardápio cadastrado no momento"
        } else {
            semPromocao.text = ""
        }
        
        return cardapioManager.cardapios.count
        
    }
    
    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cardapio_cell") as! CardapioCell
        
        let cardapio = self.cardapioManager.cardapios[indexPath.row]
        
        cell.setCardapios(cardapio: cardapio)
        return cell
    }
    
    
    
}
