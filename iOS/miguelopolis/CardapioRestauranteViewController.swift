//
//  CardapioRestauranteViewController.swift
//  miguelopolis
//
//  Created by infra on 15/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import UIKit
import SVProgressHUD
import RSWidgets
import Kingfisher
import RSStarterKit

class CardapioRestauranteViewController: UIViewController{
    
    lazy var cardapioService: CardapiosRestauranteRestService = CardapiosRestauranteService()
    var cardapioMangaer = CardapiosManager.shared
    var restaurante: Restaurante = Restaurante()
    
    @IBOutlet weak var labelSemPromocao: UILabel!
    @IBOutlet weak var tableView: UITableView!
    
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
        tableView.register(UINib(nibName: "CardapioCell", bundle: nil), forCellReuseIdentifier: "cardapio_cell")
        self.tableView.delegate = self;
        self.tableView.dataSource = self;
    }
    
    func refreshData(){
        SVProgressHUD.show()
        
        print(restaurante.id!)
        
        cardapioService.list(id: restaurante.id!) { (response) in
            SVProgressHUD.dismiss()
            if response.isSuccess{
                self.cardapioMangaer.cardapios = response.data!
                self.tableView.reloadData()
            } else {
                
            }
        }
    }
}

extension CardapioRestauranteViewController: UITableViewDelegate{
        func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
            
        }
        
}

extension CardapioRestauranteViewController: UITableViewDataSource{
        
        func numberOfSections(in tableView: UITableView) -> Int {
            return 1
        }
        
        func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
            
            if (cardapioMangaer.cardapios.count < 1) {
                labelSemPromocao.text = "Não há cardápio cadastrado no momento"
            } else {
                labelSemPromocao.text = ""
            }
            
            return cardapioMangaer.cardapios.count
            
        }
        
        public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
            let cell = tableView.dequeueReusableCell(withIdentifier: "cardapio_cell") as! CardapioCell
            
            let cardapio = self.cardapioMangaer.cardapios[indexPath.row]
            
            cell.setCardapios(cardapio: cardapio)
            return cell
        }
        
        
        
}
