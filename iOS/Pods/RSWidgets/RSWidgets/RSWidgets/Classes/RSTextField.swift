//
//  RSTextField.swift
//  RSWidgets
//
//  Created by Marcus Costa on 1/12/16.
//
//

import Foundation
import UIKit

@IBDesignable
open class RSTextField: UITextField {
    
    open var invalidClosure: (() -> ())?
    open var validClosure: (() -> ())?
    open var validateClosure:(() -> Bool)?
    
    //Input Type Delegate
    open var inputTypeDelegate: RSTextFieldInputTypeDelegate?
    
    fileprivate var pickerInputConfig = RSPickerInputConfig()
    
    @IBOutlet open var relatedLabel: UILabel?
    @IBInspectable open var isRequired: Bool = false
    
    @IBInspectable open var lineBottomWidth: Int = 0
    
    @IBInspectable open var cornerRadius: Int = 0 {
        didSet {
            layer.cornerRadius = CGFloat(cornerRadius)
        }
    }
    
    @IBInspectable open var borderWidth: Int = 0 {
        didSet {
            layer.borderWidth = CGFloat(borderWidth)
        }
    }
    
    @IBInspectable open var borderColor: UIColor = UIColor.clear {
        didSet {
            layer.borderColor = borderColor.cgColor
        }
    }
    
    @IBInspectable open var hintColor: UIColor = UIColor.clear {
        didSet {
            setValue(hintColor, forKeyPath: "_placeholderLabel.textColor")
        }
    }
    
    @IBInspectable open var textPadding: Int = 0 {
        didSet {
            leftView = nil
            rightView = nil
            
            leftViewMode = UITextFieldViewMode.never
            rightViewMode = UITextFieldViewMode.never
            
            if textPadding > 0 {
                leftView = UIView(frame: CGRect(x: 0, y: 0, width: textPadding, height: Int(bounds.size.height)))
                rightView = UIView(frame: CGRect(x: 0, y: 0, width: textPadding, height: Int(bounds.size.height)))
                
                leftViewMode = UITextFieldViewMode.always
                rightViewMode = UITextFieldViewMode.always
            }
        }
    }
    
    override open func draw(_ rect: CGRect) {
        super.draw(rect)
        if lineBottomWidth > 0 {
        
            self.borderStyle = .none
            borderColor.setStroke()
            
            if let context = UIGraphicsGetCurrentContext() {
                context.saveGState()
                context.setLineWidth(CGFloat(lineBottomWidth))
                context.move(to: CGPoint(x: 0, y: frame.size.height - CGFloat(lineBottomWidth)))
                context.addLine(to: CGPoint(x: frame.size.width, y: frame.size.height - CGFloat(lineBottomWidth)))
                context.strokePath()
                context.restoreGState()
            }
        }
    }
    
}

// MARK: - InputType
extension RSTextField {
    
    public var selectedDate: Date? {
        return pickerInputConfig.selectedDate
    }
    
    public var selectedData: RSStringPresentation? {
        return pickerInputConfig.selectedData
    }
    
    public func setPickerInputType(config: RSPickerInputConfig? = nil) {
        if config != nil {
            pickerInputConfig = config!
        }
        
        pickerInputConfig.setupDataPicker()
        pickerInputConfig.isStandAlonePicker = true
        
        self.addTarget(self, action: #selector(dateTextFieldBeginEditing), for: .editingDidBegin)
    }
    
    public func setDateInputType(config: RSPickerInputConfig? = nil) {
        if config != nil {
            pickerInputConfig = config!
        }
        
        pickerInputConfig.setupDatePicker()
        pickerInputConfig.isStandAlonePicker = false
        
        self.addTarget(self, action: #selector(dateTextFieldBeginEditing), for: .editingDidBegin)
    }
    
    public func clearInputType(config: RSPickerInputConfig? = nil) {
        self.removeTarget(self, action: #selector(dateTextFieldBeginEditing), for: .editingDidBegin)
    }
    
    @objc fileprivate func cancelButtonPressed(sender: UIBarButtonItem) {
        self.endEditing(true)
        self.inputTypeDelegate?.RSTextFieldInputTypeDidCancel(self)
    }
    
    @objc fileprivate func cleanButtonPressed(sender: UIBarButtonItem) {
        self.text = ""
        self.endEditing(true)
        self.inputTypeDelegate?.RSTextFieldInputTypeDidClean(self)
    }
    
    @objc fileprivate func confirmButtonPressed(sender: UIBarButtonItem) {
        if let selectedDate = pickerInputConfig.selectedDate {
            let dateFormatter = DateFormatter()
            dateFormatter.dateFormat = pickerInputConfig.dateFormat
//            text = dateFormatter.string(from: selectedDate)
            text = dateFormatter.string(from: pickerInputConfig.datePicker.date)
            self.inputTypeDelegate?.RSTextFieldInputType(self, didConfirmWithDate: pickerInputConfig.datePicker.date)
        }
        else if let selectedData = pickerInputConfig.selectedData {
//            text = selectedData.presentValue
            text = pickerInputConfig.presentationData?[pickerInputConfig.picker.selectedRow(inComponent: 0)].presentValue ?? ""
            
            self.inputTypeDelegate?.RSTextFieldInputType(self, didConfirmWithIndex: pickerInputConfig.picker.selectedRow(inComponent: 0))
            self.inputTypeDelegate?.RSTextFieldInputType(self, didConfirmWithValue: self.text!)
        }
        
        self.endEditing(true)
        _ = self.delegate?.textFieldShouldReturn?(self)
    }
    
    @objc fileprivate func dateTextFieldBeginEditing(_ sender: UITextField) {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = pickerInputConfig.dateFormat
        
        if pickerInputConfig.isStandAlonePicker {
            let picker = pickerInputConfig.picker
            sender.inputView = picker
            pickerInputConfig.selectedData = pickerInputConfig.presentationData?.first
        }
        else {
            let datePickerView = pickerInputConfig.datePicker
            datePickerView.date = text!.isEmpty ? Date() : dateFormatter.date(from: text!)!
            pickerInputConfig.selectedDate = datePickerView.date
            sender.inputView = datePickerView
        }
        
        let toolBar = UIToolbar(frame: CGRect(x: 0, y: 0, width: 0, height: 44))
        toolBar.barStyle = pickerInputConfig.toolbarStyle
        toolBar.isTranslucent = pickerInputConfig.toolBarTranslucent
        if toolBar.isTranslucent {
            toolBar.backgroundColor = pickerInputConfig.toolbarColor
        }
        else {
            toolBar.barTintColor = pickerInputConfig.toolbarColor
        }
        toolBar.tintColor = pickerInputConfig.toolBarTintColor
        
        
        let cancelBarButton = UIBarButtonItem(title: pickerInputConfig.cancelButtonTitle, style: .plain, target: self, action: #selector(cancelButtonPressed))
        
        let cleanBarButton = UIBarButtonItem(title: pickerInputConfig.cleanButtonTitle, style: .plain, target: self, action: #selector(cleanButtonPressed))
        
        let confirmBarButton = UIBarButtonItem(title: pickerInputConfig.confirmButtonTitle, style: .done, target: self, action: #selector(confirmButtonPressed))
        
        let flexibleItem = UIBarButtonItem(barButtonSystemItem: .flexibleSpace, target: nil, action: nil)
        
        if pickerInputConfig.cleanButtonTitle.characters.count > 0 {
            toolBar.items = [cancelBarButton, flexibleItem, cleanBarButton, confirmBarButton]
        }
        else {
            toolBar.items = [cancelBarButton, flexibleItem, confirmBarButton]
        }
        
        self.inputAccessoryView = toolBar
    }
}


// MARK: - Validation
extension RSTextField {
    
    public class func hasInvalidFields(`in` textFields: [RSTextField]) -> [RSTextField]? {
        
        var invalidFields = [RSTextField]()
        
        textFields.forEach { (textField) in
            if textField.isRequired
            {
                if textField.text?.trimmingCharacters(in: CharacterSet.whitespaces).characters.count == 0 {
                    
                    invalidFields.append(textField)

                    invalidateTextField(textField)
                    
                }
                else {
                    if let validateClosure = textField.validateClosure {
                        if validateClosure() {
                            validateTextField(textField)
                        }
                        else {
                            
                            invalidFields.append(textField)
                            
                            invalidateTextField(textField)
                        }
                    }
                    else {
                        validateTextField(textField)
                    }
                }
            }
            else if let validateClosure = textField.validateClosure {
                
                if textField.text?.trimmingCharacters(in: CharacterSet.whitespaces).characters.count == 0 {
                    validateTextField(textField)
                }
                else if validateClosure() {
                    validateTextField(textField)
                }
                else {
                    
                    invalidFields.append(textField)
                    
                    invalidateTextField(textField)
                }
            }
            else {
                validateTextField(textField)
            }
        }
        
        return invalidFields.count > 0 ? invalidFields : nil
    }
    
    public class func isValid(_ textFields: [RSTextField]) -> Bool {
        
        if let _ = hasInvalidFields(in: textFields) {
            return false
        }
        else {
            return true
        }
        
    }
    
    fileprivate class func invalidateTextField(_ textField: RSTextField) {
        if let invalidClosure = textField.invalidClosure {
            invalidClosure()
        }
    }
    
    fileprivate class func validateTextField(_ textField: RSTextField) {
        if let validClosure = textField.validClosure {
            validClosure()
        }
    }
}

// MARK: - Setup validators
extension RSTextField {
    
    public func setCnpjValidator() {
        validateClosure = {
            
            if !self.isRequired, self.text!.isEmpty {
                return true
            }
            
            guard let text = self.text, text.matchPattern("^(\\d{2}.?\\d{3}.?\\d{3}/?\\d{4}-?\\d{2})$") else {
                return false
            }
            
            let cnpj = text.replacingOccurrences(of: ".", with: "").replacingOccurrences(of: "-", with: "").replacingOccurrences(of: "/", with: "")
            
            var sum = 0
            var weight = 0
            
            let d1 = Int( cnpj.substring(with: Range(uncheckedBounds: (lower: cnpj.index(cnpj.startIndex, offsetBy: 12), upper: cnpj.index(cnpj.startIndex, offsetBy: 13))) ) )!
            let d2 = Int( cnpj.substring(with: Range(uncheckedBounds: (lower: cnpj.index(cnpj.startIndex, offsetBy: 13), upper: cnpj.index(cnpj.startIndex, offsetBy: 14))) ) )!
            
            var temp1 = 0
            var temp2 = 0
            
            if Set(cnpj.characters).count == 1 {
                return false
            }
            
            //Verificação 13 Digito
            weight = 2
            for index in (0...11).reversed() {
                let digit = Int( cnpj.substring(with: Range(uncheckedBounds: (lower: cnpj.index(cnpj.startIndex, offsetBy: index), upper: cnpj.index(cnpj.startIndex, offsetBy: index + 1))) ) )!
                sum = sum + (digit * weight)
                
                weight += 1
                
                if weight == 10 {
                    weight = 2
                }
            }
            
            if sum % 11 == 0 || sum % 11 == 1 {
                temp1 = 0
            }
            else {
                temp1 = 11 - sum % 11
            }
            
            //Verificação 14 Digito
            sum = 0
            weight = 2
            
            for index in (0...12).reversed() {
                let digit = Int( cnpj.substring(with: Range(uncheckedBounds: (lower: cnpj.index(cnpj.startIndex, offsetBy: index), upper: cnpj.index(cnpj.startIndex, offsetBy: index + 1))) ) )!
                sum = sum + (digit * weight)
                
                weight += 1
                
                if weight == 10 {
                    weight = 2
                }
            }
            
            if sum % 11 == 0 || sum % 11 == 1 {
                temp2 = 0
            }
            else {
                temp2 = 11 - sum % 11
            }
            
            if temp1 == d1 && temp2 == d2 {
                return true
            }
            
            return false
        }
    }
    
    public func setCpfValidator() {
        validateClosure = {
            
            if !self.isRequired, self.text!.isEmpty {
                return true
            }
            
            guard let text = self.text, text.matchPattern("(^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2})$") else {
                return false
            }
            
            let cpf = text.replacingOccurrences(of: ".", with: "").replacingOccurrences(of: "-", with: "")
            
            let d1 = Int( cpf.substring(with: Range(uncheckedBounds: (lower: cpf.index(cpf.startIndex, offsetBy: 9), upper: cpf.index(cpf.startIndex, offsetBy: 10))) ) )!
            let d2 = Int( cpf.substring(with: Range(uncheckedBounds: (lower: cpf.index(cpf.startIndex, offsetBy: 10), upper: cpf.index(cpf.startIndex, offsetBy: 11))) ) )!
            
            var temp1 = 0
            var temp2 = 0
            
            if Set(cpf.characters).count == 1 {
                return false
            }
            
            for index in 0...8 {
                let char = Int( cpf.substring(with: Range(uncheckedBounds: (lower: cpf.index(cpf.startIndex, offsetBy: index), upper: cpf.index(cpf.startIndex, offsetBy: index+1)))))!
                
                temp1 += char * (10 - index)
                temp2 += char * (11 - index)
            }
            
            temp1 %= 11
            temp1 = temp1 < 2 ? 0 : 11 - temp1
            
            temp2 += temp1 * 2
            temp2 %= 11
            temp2 = temp2 < 2 ? 0 : 11 - temp2
            
            if temp1 == d1 && temp2 == d2 {
                return true
            }
            
            return false
        }
    }
    
    public func setStrengthPasswordValidator() {
        validateClosure = {
            guard let text = self.text, text.matchPattern("[a-z]+"), text.matchPattern("[A-Z]+"), text.matchPattern("\\d+"), text.matchPattern("[^0-9a-zA-Z]"), text.matchPattern(".{8,}") else {
                return false
            }
            
            return true
        }
    }
    
    public func setEmailValidator() {
        validateClosure = {
            guard let text = self.text else {
                return false
            }
            
            let trimmedText = text.trimmingCharacters(in: CharacterSet.whitespacesAndNewlines)
            self.text = trimmedText
            guard trimmedText.matchPattern("^(([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5}){1,25})$") else {
                return false
            }
            
            return true
        }
    }
    
}

//MARK: - Validates (shouldChangeCharactersIn range)
extension RSTextField {
    
    public static func validateTextField(textField: UITextField, shouldReplaceString string: String, inRange range: NSRange, withCharacterLimit limit: Int) -> Bool {
        let nsString = textField.text! as NSString
        return nsString.replacingCharacters(in: range, with: string).characters.count <= limit
    }
    
    public static func validateDoubleField(textField: UITextField, shouldReplaceString string: String, inRange range: NSRange, decimalSymbol: String = ".") -> Bool {
//        let decimalSymbol = String(withCustomIdentifier: StringIdentifier.CommomDecimalSymbol)
        
        let newCharacters = CharacterSet(charactersIn: string)
        let boolIsNumber = CharacterSet.decimalDigits.isSuperset(of: newCharacters)
        if boolIsNumber == true {
            return true
        } else {
            if string == decimalSymbol {
                let countdots = textField.text!.components(separatedBy: decimalSymbol).count - 1
                if countdots == 0 {
                    return true
                } else {
                    if countdots > 0 && string == decimalSymbol {
                        return false
                    } else {
                        return true
                    }
                }
            } else {
                return false
            }
        }
    }
    
    public static func validateNumericField(textField: UITextField, shouldReplaceString string: String, inRange range: NSRange) -> Bool {
        let newCharacters = CharacterSet(charactersIn: string)
        let boolIsNumber = CharacterSet.decimalDigits.isSuperset(of: newCharacters)
        return boolIsNumber
        
        
        // NSCharacterSet.decimalDigitCharacterSet().isSupersetOfSet(newCharacters)
    }
    
    public static func validateNumericWithSpecialCharactersField(textField: UITextField, shouldReplaceString string: String, inRange range: NSRange) -> Bool {
        let digits = CharacterSet(charactersIn: "1234567890./-()")
        let result = string.trimmingCharacters(in: digits)
        
        return result.isEmpty
    }
    
}

// MARK: - Format
extension RSTextField {
    
    public static func setupRequired(textFields: [RSTextField], withPrefix: Bool = true, symbol: String = "*") {
        textFields.forEach { (textField) in
            if textField.isRequired {
                if withPrefix {
                    if let relatedLabel = textField.relatedLabel, !relatedLabel.text!.hasPrefix(symbol) {
                        relatedLabel.text = "\(symbol)\(relatedLabel.text!)"
                    }
                }
                else {
                    if let relatedLabel = textField.relatedLabel, !relatedLabel.text!.hasSuffix(symbol) {
                        relatedLabel.text = "\(relatedLabel.text!)\(symbol)"
                    }
                }
            }
        }
    }
    
}

extension String {
    func matchPattern(_ patStr:String)->Bool {
        var isMatch:Bool = false
        do {
            let regex = try NSRegularExpression(pattern: patStr, options: [.caseInsensitive])
            let result = regex.firstMatch(in: self, options: NSRegularExpression.MatchingOptions(rawValue: 0), range: NSMakeRange(0, characters.count))
            
            if (result != nil) {
                isMatch = true
            }
        }
        catch {
            isMatch = false
        }
        return isMatch
    }
}






