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
        definesPresentationContext = true
        
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
        let novaDescricao = descricao.folding(options: .diacriticInsensitive, locale: .current)
        
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
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        
        let utilitario = self.utilitarioManager.utilitarios[indexPath.row]
        
        let popOverVC = storyboard?.instantiateViewController(withIdentifier: "sbPopUpId") as! PopUpViewController
        popOverVC.modalPresentationStyle = .overCurrentContext
        popOverVC.navigationController?.modalPresentationStyle = .overCurrentContext
        
        popOverVC.utilitario = utilitario
        
        self.present(popOverVC, animated: true, completion: nil)
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
