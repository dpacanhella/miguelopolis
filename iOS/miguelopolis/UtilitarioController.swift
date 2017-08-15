//
//  LojaViewController.swift
//  miguelopolis
//
//  Created by infra on 04/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//
import Foundation
import UIKit
import SVProgressHUD

class UtilitarioViewController: UIViewController{
    
    @IBOutlet weak var collectionView: UICollectionView!
    
    lazy var utilitarioService: UtilitarioRestService = UtilitarioService()
    
    var utilitarioManager = UtilitarioManager.shared
    
    var tipoAnuncio: TipoAnuncio = TipoAnuncio()
    
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
        collectionView.register(UINib(nibName: "UtilitarioCell", bundle: nil), forCellWithReuseIdentifier: "utilitario_cell")
        self.collectionView.delegate = self;
        self.collectionView.dataSource = self;
        
    }
    
    func refreshData(){
            
        let descricao = tipoAnuncio.descricao!
        var novaDescricao = descricao.replacingOccurrences(of: "SERVIÇOS", with: "SERVICOS")
        
        if (descricao == "DISK-GÁS") {
            novaDescricao = descricao.replacingOccurrences(of: "DISK-GÁS", with: "DISK-GAS")
        }
        
        SVProgressHUD.show()
        utilitarioService.list(tipoAnuncio: novaDescricao) { (response) in
            SVProgressHUD.dismiss()
            if response.isSuccess{
                self.utilitarioManager.utilitarios = response.data!
                self.collectionView.reloadData()
            } else {
                    
            }
        }
    }
    
}

extension UtilitarioViewController: UICollectionViewDelegate{
    private func collectionView(_ collectionView: UICollectionView, didSelectRowAt indexPath: IndexPath) {
        
    }
}

extension UtilitarioViewController: UICollectionViewDataSource{
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return utilitarioManager.utilitarios.count
        
    }
    
    public func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "utilitario_cell", for: indexPath) as! UtilitarioCell
        
        let utilitarios = self.utilitarioManager.utilitarios[indexPath.row]
        
        cell.setUtilitarios(utilitarios: utilitarios)
        
        cell.layer.borderWidth = 0.5
        cell.layer.borderColor = UIColor.gray.cgColor
        cell.layer.shadowColor = UIColor.gray.cgColor
        cell.layer.shadowOffset = CGSize(width: 0, height: 2.0)
        cell.layer.cornerRadius = 4
        cell.layer.masksToBounds = false
        
        
        return cell
    }

}
