//
//  File.swift
//  Pods
//
//  Created by Marcus Costa on 14/02/17.
//
//

import Foundation
import UIKit

public class RSPickerInputConfig: NSObject {
    
    public static var `default`: RSPickerInputConfig {
        return RSPickerInputConfig()
    }
    
    public var dateFormat = RSPickerInputConfig.appearance.pickerDateFormat
    
    public var confirmButtonTitle = RSPickerInputConfig.appearance.pickerToolbarConfirmTitle
    public var cancelButtonTitle = RSPickerInputConfig.appearance.pickerToolbarCancelTitle
    public var cleanButtonTitle = RSPickerInputConfig.appearance.pickerToolbarCleanTitle
    
    public var toolbarStyle = RSPickerInputConfig.appearance.pickerToolBarStyle
    public var toolbarColor = RSPickerInputConfig.appearance.pickerToolBarColor
    public var toolBarTintColor = RSPickerInputConfig.appearance.pickerToolBarTintColor
    public var toolBarTranslucent = RSPickerInputConfig.appearance.pickerToolbarTranslucent
    
    public var datePickerMode = UIDatePickerMode.date
    
    public var datePicker = UIDatePicker()
    public var picker = UIPickerView()
    public var presentationData: [RSStringPresentation]?
    
    public internal(set) var selectedData: RSStringPresentation?
    public internal(set) var selectedDate: Date?
    
    public func updateSelectedData(data: RSStringPresentation) {
        selectedData = data
    }
    
    internal var isStandAlonePicker = false
    
    internal func setupDatePicker() {
        datePicker.datePickerMode = datePickerMode
        datePicker.addTarget(self, action: #selector(datePickerValueChanged), for: .valueChanged)
    }
    
    internal func setupDataPicker() {
        guard presentationData != nil else {
            return
        }
        
        if picker.delegate == nil {
            picker.delegate = self
        }
        
        if picker.dataSource == nil {
            picker.dataSource = self
        }
    }
    
    @objc fileprivate func datePickerValueChanged(sender:UIDatePicker) {
        selectedDate = sender.date
    }
    
}

// MARK: - Appearance Values
extension RSPickerInputConfig {
    
    public struct appearance {
        
        public static var pickerToolBarStyle: UIBarStyle = .default
        public static var pickerToolBarColor: UIColor = UIColor.black
        public static var pickerToolBarTintColor: UIColor = UIColor.white
        public static var pickerDateFormat: String = "dd/MM/yyyy"
        public static var pickerToolbarConfirmTitle: String = "Ok"
        public static var pickerToolbarCancelTitle: String = "Cancel"
        public static var pickerToolbarCleanTitle: String = "Clean"
        public static var pickerToolbarTranslucent: Bool = false
        
    }
    
}

extension RSPickerInputConfig: UIPickerViewDelegate {
    
    public func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        selectedData = presentationData![row]
    }
    
}

extension RSPickerInputConfig: UIPickerViewDataSource {
    
    public func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    public func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return presentationData?.count ?? 0
    }
    
    public func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return presentationData?[row].presentValue
    }
    
}














