//
//  LanchoneteViewController.swift
//  miguelopolis
//
//  Created by infra on 04/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import UIKit
import SVProgressHUD

class LanchoneteViewController: UIViewController{

    @IBOutlet weak var collectionView: UICollectionView!

    lazy var lanchoneteService: LanchoneteRestService = LanchoneteService()
    
    var lanchoneteManager = LanchoneteManager.shared
    
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
        collectionView.register(UINib(nibName: "LanchoneteCell", bundle: nil), forCellWithReuseIdentifier: "lanchonete_cell")
        self.collectionView.delegate = self;
        self.collectionView.dataSource = self;
        
    }
    
    func refreshData(){
        if lanchoneteManager.lanchonetes.isEmpty{
            
            SVProgressHUD.show()
            lanchoneteService.list { (response) in
                SVProgressHUD.dismiss()
                if response.isSuccess{
                    self.lanchoneteManager.lanchonetes = response.data!
                    self.collectionView.reloadData()
                } else {
                    
                }
            }
        }
    }
    
}

extension LanchoneteViewController: UICollectionViewDelegate{
    private func collectionView(_ collectionView: UICollectionView, didSelectRowAt indexPath: IndexPath) {
        
    }
}

extension LanchoneteViewController: UICollectionViewDataSource{
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        
        return lanchoneteManager.lanchonetes.count
        
    }
    
    public func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "lanchonete_cell", for: indexPath) as! LanchoneteCell
        
        let lanchonete = self.lanchoneteManager.lanchonetes[indexPath.row]
        
        
        cell.setLanchonetes(lanchonete: lanchonete)
        
        cell.layer.borderWidth = 0.5
        cell.layer.borderColor = UIColor.gray.cgColor
        cell.layer.shadowColor = UIColor.gray.cgColor
        cell.layer.shadowOffset = CGSize(width: 0, height: 2.0)
        cell.layer.cornerRadius = 4
        cell.layer.masksToBounds = false
        
        
        return cell
    }
    
}
