//
//  RSPresentationTableView.swift
//  Pods
//
//  Created by Marcus Costa on 14/02/17.
//
//

import UIKit

public protocol RSPresentationTableViewDelegate {
    
    func presentationTableView(presentationTableView: RSPresentationTableView, didChangeSelectedItems items: [RSStringPresentation])
    
}

public class RSPresentationTableView: UITableView {
    
    public enum SelectionListType {
        case single
        case multi
    }

    public var selectionType = SelectionListType.single
    public var isSearchBarEnable = true
    public var nib: UINib?
    public var selectedItems = [RSStringPresentation]()
    public var heightForRow: CGFloat = 44.0
    public var presentationTableViewDelegate: RSPresentationTableViewDelegate?
    public var source: (() -> [RSStringPresentation]) = { return [RSStringPresentation]() } {
        didSet {
            sourceData = source()
        }
    }
    
    fileprivate var searchBar: UISearchBar!
    
    fileprivate var sourceData = [RSStringPresentation]() {
        didSet {
            searchBar.delegate?.searchBar?(searchBar, textDidChange: searchBar.text ?? "")
        }
    }
    fileprivate var searchData: [RSStringPresentation]?
    
    fileprivate var data: [RSStringPresentation] {
        return searchData == nil ? sourceData : searchData!
    }
    
    fileprivate let cellId = "PresentationCell"
    
    // MARK: - Constructors
    required public init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        
        setup()
    }
    
    override init(frame: CGRect, style: UITableViewStyle) {
        super.init(frame: frame, style: style)
        
        setup()
    }
    
    // MARK: - Setup Methods
    fileprivate func setup() {
        setupSearchBar()
        delegate = self
        dataSource = self
        allowsMultipleSelection = selectionType == .multi
        separatorStyle = .none
        
        if let nib = nib {
            register(nib, forCellReuseIdentifier: cellId)
        }
        else {
            register(UITableViewCell.self, forCellReuseIdentifier: cellId)
        }
        
    }
    
    fileprivate func setupSearchBar() {
        searchBar = UISearchBar(frame: CGRect(x: 0, y: 0, width: frame.size.width, height: 44))
        searchBar.delegate = self
        
        if isSearchBarEnable {
            self.tableHeaderView = searchBar
        }
    }
    
    fileprivate func setSelected() {
        guard data.count > 0, selectedItems.count > 0 else {
            return
        }
        
        for (idx, stringPresented) in data.enumerated() {
            if selectedItems.contains(where: { (item) -> Bool in
                return item.presentValue == stringPresented.presentValue
            }) {
                selectRow(at: IndexPath(row: idx, section: 0), animated: false, scrollPosition: UITableViewScrollPosition.none)
            }
        }
    }
    
    /*
    // Only override draw() if you perform custom drawing.
    // An empty implementation adversely affects performance during animation.
    override func draw(_ rect: CGRect) {
        // Drawing code
    }
    */

    
}

extension RSPresentationTableView: UISearchBarDelegate {
    
    public func searchBarTextDidBeginEditing(_ searchBar: UISearchBar) {
        searchBar.setShowsCancelButton(true, animated: true)
    }
    
    public func searchBarCancelButtonClicked(_ searchBar: UISearchBar) {
        searchBar.setShowsCancelButton(false, animated: true)
        searchBar.text = ""
        searchBar.resignFirstResponder()
        searchData = nil
        
        reloadData()
        setSelected()
    }
    
    public func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        if searchText.characters.count > 0 {
            searchData = sourceData.filter({( element: RSStringPresentation) -> Bool in
                if let searchable = element as? RSSearchable {
                    return searchable.contains(term: searchText)
                }
                
                return true
            })
        }
        else {
            searchData = nil
        }
        
        reloadData()
        setSelected()
    }
    
}

// MARK: - UITableViewDataSource
extension RSPresentationTableView: UITableViewDataSource {
    
    public func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return data.count
    }
    
    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        var cell = tableView.dequeueReusableCell(withIdentifier: cellId, for: indexPath)
        
        if cell == nil {
            cell = UITableViewCell(style: .default, reuseIdentifier: cellId)
        }
        
        if let presentationCell = cell as? RSPresentationTableViewCell {
            presentationCell.setPresentationValue(presentation: data[indexPath.row])
        }
        
        return cell
    }
    
}

// MARK: - UITableViewDelegate
extension RSPresentationTableView: UITableViewDelegate {
    
    public func tableView(_ tableView: UITableView, willSelectRowAt indexPath: IndexPath) -> IndexPath? {
        guard selectionType == .multi else {
            return indexPath
        }
        
        let selected = indexPathsForSelectedRows?.filter{ ($0 as IndexPath).row == (indexPath as IndexPath).row && ($0 as IndexPath).section == (indexPath as IndexPath).section }
        if let selected = selected , selected.count > 0 {
            deselectRow(at: indexPath, animated: false)
            
            if selectionType == .multi {
                if let index = selectedItems.index(where: { $0.presentValue == data[indexPath.row].presentValue }) {
                    selectedItems.remove(at: index)
                }
            }
            
            presentationTableViewDelegate?.presentationTableView(presentationTableView: self, didChangeSelectedItems: selectedItems)
            
            return nil
        }
        else {
            return indexPath
        }
    }
    
    public func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if selectionType == .single {
            selectedItems = [data[(indexPath as IndexPath).row]]
        }
        else {
            selectedItems.append(data[indexPath.row])
        }
        
        presentationTableViewDelegate?.presentationTableView(presentationTableView: self, didChangeSelectedItems: selectedItems)
    }
    
    public func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return heightForRow
    }
    
}














