//
//  RSCurrencyTextField.swift
//  Pods
//
//  Created by Marcus Costa on 13/03/17.
//
//

import Foundation

open class RSCurrencyTextField: RSTextField {
    
    open static var defaultCurrencyFormatter: NumberFormatter?
    
    fileprivate var invalidInputCharacterSet: CharacterSet?
    open var currencyNumberFormatter: NumberFormatter?
    open var amount: NSNumber {
        get {
            return amount(from: text ?? "")
        }
        set {
            text = currencyNumberFormatter!.string(from: newValue)
        }
    }
    
    open var currencyDelegate: UITextFieldDelegate?
    
    public override init(frame: CGRect) {
        super.init(frame: frame)
        commonInit()
    }
    
    public required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        commonInit()
    }
    
    open func setText(text: String) {
        let formatted = currencyNumberFormatter!.string(from: amount(from: text))
        self.text = formatted
    }
    
    fileprivate func commonInit() {
        invalidInputCharacterSet = CharacterSet.decimalDigits.inverted
        
        if let formatter = RSCurrencyTextField.defaultCurrencyFormatter {
            currencyNumberFormatter = formatter
            currencyNumberFormatter?.numberStyle = .currency
        }
        else {
            currencyNumberFormatter = NumberFormatter()
            currencyNumberFormatter?.locale = Locale.current
            currencyNumberFormatter?.numberStyle = .currency
            currencyNumberFormatter?.usesGroupingSeparator = true
        }
        
        delegate = self
        
        setText(text: "0")
    }
    
    fileprivate func setCaratPosition(position: Int) {
        setSelectionRange(range: NSRange(location: position, length: 0))
    }
    
    fileprivate func setSelectionRange(range: NSRange) {
        let start = position(from: beginningOfDocument, offset: range.location)
        let end = position(from: start!, offset: range.length)
        
        selectedTextRange = textRange(from: start!, to: end!)
    }
    
    fileprivate func amount(from string: String) -> NSNumber {
        let digitString = string.components(separatedBy: invalidInputCharacterSet!).joined(separator: "")
        assert(currencyNumberFormatter!.maximumFractionDigits == currencyNumberFormatter!.minimumFractionDigits)
        let fd = currencyNumberFormatter!.minimumFractionDigits
        let number = NSNumber(floatLiteral: Double(digitString)! / pow(Double(10.0), Double(fd)))
        
        return number
    }
    
}

extension RSCurrencyTextField: UITextFieldDelegate {
    
    public func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        if string.characters.count == 0, range.length == 1, invalidInputCharacterSet!.contains(Array(text!.characters)[range.location]) {
            setCaratPosition(position: range.location)
            return false
        }
        
        let distanceFromEnd = text!.characters.count - (range.location + range.length)
        let changed = (text! as! NSString).replacingCharacters(in: range, with: string)
        setText(text: changed)
        
        let pos = text!.characters.count - distanceFromEnd
        if pos >= 0, pos <= text!.characters.count {
            setCaratPosition(position: pos)
        }
        
        sendActions(for: .editingChanged)
        
        return false
    }
    
    public func textFieldShouldBeginEditing(_ textField: UITextField) -> Bool {
        return currencyDelegate?.textFieldShouldBeginEditing?(textField) ?? true
    }
    
    public func textFieldDidBeginEditing(_ textField: UITextField) {
        currencyDelegate?.textFieldDidBeginEditing?(textField)
    }
    
    public func textFieldShouldEndEditing(_ textField: UITextField) -> Bool {
        return currencyDelegate?.textFieldShouldEndEditing?(textField) ?? true
    }
    
    public func textFieldDidEndEditing(_ textField: UITextField){
        currencyDelegate?.textFieldDidEndEditing?(textField)
    }
    
    @available(iOS 10.0, *)
    public func textFieldDidEndEditing(_ textField: UITextField, reason: UITextFieldDidEndEditingReason) {
        currencyDelegate?.textFieldDidEndEditing?(textField, reason: reason)
    }
    
    public func textFieldShouldClear(_ textField: UITextField) -> Bool {
        return currencyDelegate?.textFieldShouldClear?(textField) ?? true
    }
    
    public func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        return currencyDelegate?.textFieldShouldReturn?(textField) ?? false
    }
    
}

extension CharacterSet {
    func contains(_ character: Character) -> Bool {
        let string = String(character)
        return string.rangeOfCharacter(from: self, options: [], range: string.startIndex..<string.endIndex) != nil
    }
}





























