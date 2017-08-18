//
//  RestauranteViewController.swift
//  miguelopolis
//
//  Created by infra on 03/08/17.
//  Copyright © 2017 pacanhella. All rights reserved.
//

import Foundation
import UIKit
import SVProgressHUD

class BarViewController: UIViewController{
    
    @IBOutlet weak var collectionView: UICollectionView!
    
    lazy var restauranteService: RestauranteRestService = RestauranteService()
    
    var restauranteManager = RestauranteManager.shared
    
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
        collectionView.register(UINib(nibName: "RestauranteCell", bundle: nil), forCellWithReuseIdentifier: "restaurante_cell")
                self.collectionView.delegate = self;
        self.collectionView.dataSource = self;

    }
    
    func refreshData(){
        if restauranteManager.restaurantes.isEmpty{
            
            SVProgressHUD.show()
            restauranteService.list { (response) in
                SVProgressHUD.dismiss()
                if response.isSuccess{
                    self.restauranteManager.restaurantes = response.data!
                    self.collectionView.reloadData()
                } else {
                    
                }
            }
        }
    }
    
}

extension BarViewController: UICollectionViewDelegate {
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        performSegue(withIdentifier: "segueRestauranteController", sender: self)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?){
        let tabBar =  segue.destination as! UITabBarController
        let telaDetalhes = tabBar.viewControllers![0] as! DetalhesRestauranteViewController
        let telaEventos = tabBar.viewControllers![1] as! EventoRestauranteViewController
        let telaCardapios = tabBar.viewControllers![2] as! CardapioRestauranteViewController
        
        if let indexPath = self.collectionView.indexPathsForSelectedItems?.first!{
            
            let restaurante = self.restauranteManager.restaurantes[indexPath.row]
            
            telaDetalhes.restaurante = restaurante
            telaEventos.restaurante = restaurante
            telaCardapios.restaurante = restaurante
        }
    }
}

extension BarViewController: UICollectionViewDataSource{
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        
        return restauranteManager.restaurantes.count
        
    }
    
    public func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "restaurante_cell", for: indexPath) as! RestauranteCell
        
        let restaurante = self.restauranteManager.restaurantes[indexPath.row]
        
        cell.setRestaurantes(restaurante: restaurante)
        
        cell.layer.borderWidth = 0.5
        cell.layer.borderColor = UIColor.gray.cgColor
        cell.layer.shadowColor = UIColor.gray.cgColor
        cell.layer.shadowOffset = CGSize(width: 0, height: 2.0)
        cell.layer.cornerRadius = 4
        cell.layer.masksToBounds = false

        
        return cell
    }
    
}
