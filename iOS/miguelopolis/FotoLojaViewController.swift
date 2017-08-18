//
//  FotoLojaViewController.swift
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

class FotoLojaViewController: UIViewController{
    
    var loja: Loja = Loja()
    lazy var imagemLojaService: ImagemLojaRestService = ImagemLojaService()
    var imagemLojaManager = ImagemLojaManager.shared

    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var semProdutos: UILabel!
    
    let cellReuseIdentifier = "cell"
    let cellSpacingHeight: CGFloat = 5
    
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
        tableView.register(UINib(nibName: "FotoCell", bundle: nil), forCellReuseIdentifier: "foto_cell")
        self.tableView.delegate = self;
        self.tableView.dataSource = self;
    }
    
    func refreshData(){
        SVProgressHUD.show()
        
        print(loja.id!)
        
        imagemLojaService.list(id: loja.id!) { (response) in
            SVProgressHUD.dismiss()
            if response.isSuccess{
                self.imagemLojaManager.imagens = response.data!
                self.tableView.reloadData()
            } else {
                
            }
        }
    }
}

extension FotoLojaViewController: UITableViewDelegate{
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
    }
}

extension FotoLojaViewController: UITableViewDataSource{
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        if (imagemLojaManager.imagens.count < 1) {
            semProdutos.text = "Não há produtos cadastrado no momento"
        } else {
            semProdutos.text = ""
        }
        
        return imagemLojaManager.imagens.count
        
    }
    
    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "foto_cell") as! FotoCell
        
        let imagem = self.imagemLojaManager.imagens[indexPath.row]
        
        cell.setFotos(imagem: imagem)
        
        
        cell.backgroundColor = UIColor.white
        cell.layer.borderColor = UIColor.black.cgColor
        cell.layer.borderWidth = 1
        cell.layer.cornerRadius = 8
        cell.clipsToBounds = true
        
        return cell
    }
    
    
    
}




