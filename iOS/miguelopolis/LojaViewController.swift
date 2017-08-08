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

class LojaViewController: UIViewController{

    @IBOutlet weak var collectionView: UICollectionView!
    lazy var lojaService: LojaRestService = LojaService()
    
    var lojaManager = LojaManager.shared
    
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
        collectionView.register(UINib(nibName: "LojaCell", bundle: nil), forCellWithReuseIdentifier: "loja_cell")
        self.collectionView.delegate = self;
        self.collectionView.dataSource = self;
        
    }
    
    func refreshData(){
        if lojaManager.lojas.isEmpty{
            
            SVProgressHUD.show()
            lojaService.list { (response) in
                SVProgressHUD.dismiss()
                if response.isSuccess{
                    self.lojaManager.lojas = response.data!
                    self.collectionView.reloadData()
                } else {
                    
                }
            }
        }
    }
    
}

extension LojaViewController: UICollectionViewDelegate{
    private func collectionView(_ collectionView: UICollectionView, didSelectRowAt indexPath: IndexPath) {
        
    }
}

extension LojaViewController: UICollectionViewDataSource{
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return lojaManager.lojas.count
        
    }
    
    public func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "loja_cell", for: indexPath) as! LojaCell
        
        let loja = self.lojaManager.lojas[indexPath.row]
        
        cell.setLojas(loja: loja)
        
        cell.layer.borderWidth = 0.5
        cell.layer.borderColor = UIColor.gray.cgColor
        cell.layer.shadowColor = UIColor.gray.cgColor
        cell.layer.shadowOffset = CGSize(width: 0, height: 2.0)
        cell.layer.cornerRadius = 4
        cell.layer.masksToBounds = false
        
        
        return cell
    }
    
}
