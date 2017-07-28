//
//  RSMaskedTextField.swift
//  Pods
//
//  Created by Andre M. Della Torre on 8/3/16.
//
//

import Foundation

import UIKit

open class RSMaskedTextField : RSTextField {
    
    @IBInspectable open var textMask: String = "####"
    @IBInspectable open var allowsEditingBetweenCharacters: Bool = false
    
    open var textMasks: [String]? {
        didSet {
            if let textMasks = textMasks, textMasks.count > 0 {
                
                if textMasks.count == 1 {
                    textMask = textMasks.first!
                }
                else {
                    textMask = (textMasks.sorted { $0.0.characters.count > $0.1.characters.count }).first!
                }
            }
        }
    }
    
    open func shouldChangeCharactersInRange(_ range: NSRange, replacementString string: String) -> Bool {
        
        let resultString = (text as! NSString).replacingCharacters(in: range, with: string)
        
        if let newTextMask = getBestMask(to: resultString), newTextMask != textMask {
            let unmaskedText = removeMask(from: resultString)
            textMask = newTextMask
            
            return applyMask(to: unmaskedText)
            
        }
        else if !allowsEditingBetweenCharacters {
            let minAllowedLocation = (self.text?.characters.count)! - 1
            let editLocation = range.location
            
            if editLocation < minAllowedLocation {
                self.resignFirstResponder()
                self.becomeFirstResponder()
                return false
            }
            
            return RSMaskEditor.shouldChangeCharactersInRange(range, replacementString: string, textField: self, mask: textMask)
        }
        
        return false
    }
    
    open func changeMask(to newMask: String) {
        
        let origText = originalText()
        textMask = newMask
        applyMask(to: origText)
    }
    
    open func originalText() -> String {
        
        return removeMask(from: text!)
    }
    
    fileprivate func removeMask(from string: String) -> String {
        
        var unmeskedText = String()
        
        let digits = CharacterSet.decimalDigits
        let letters = CharacterSet.letters
        
        for character in string.characters {4
            
            let charString = "\(character)"
            
            if digits.isSuperset(of: CharacterSet(charactersIn:charString)) || letters.isSuperset(of: CharacterSet(charactersIn:charString)) {
                unmeskedText.append(character)
            }
        }
        
        return unmeskedText
    }
    
    fileprivate func getBestMask(to string: String) -> String? {
        
        guard let textMasks = textMasks, textMasks.count > 0 else {
            return nil
        }
        
        let orderedTextMasks = textMasks.sorted { $0.0.characters.count > $0.1.characters.count }
        
        for textMask in textMasks {
            if textMask.characters.count >= string.characters.count {
                return textMask
            }
        }
        
        return nil
        
    }
    
    fileprivate func applyMask(to currentText: String) -> Bool {
        
        let digits = CharacterSet.decimalDigits
        let letters = CharacterSet.letters
        
        
        var returnText = String()
        
        var charDelayIndex: Int = 0
        
        for idx in 0..<max(textMask.characters.count, currentText.characters.count) {
            
            guard idx < textMask.characters.count, idx - charDelayIndex < currentText.characters.count else {
                text = returnText
                return false
            }
            
            let localRange = Range(uncheckedBounds: (lower: textMask.characters.index(textMask.startIndex, offsetBy: idx), upper: textMask.characters.index(textMask.startIndex, offsetBy: idx + 1 )))
            let localRangeChar = Range(uncheckedBounds: (lower: currentText.characters.index(currentText.startIndex, offsetBy: (idx - charDelayIndex)), upper: currentText.characters.index(currentText.startIndex, offsetBy: (idx - charDelayIndex + 1) )))
            
            let characterMask = textMask[localRange]
            
            let character = currentText[localRangeChar]
            let charString = "\(character)"
            
            if digits.isSuperset(of: CharacterSet(charactersIn:charString)) && characterMask == "#" {
                returnText.append(character)
            }
            else if letters.isSuperset(of: CharacterSet(charactersIn:charString)) && characterMask == "@" {
                returnText.append(character)
            }
            else {
                if String(characterMask) == "#" || String(characterMask) == "@" {
                    break
                }
                returnText.append(characterMask)
                
                charDelayIndex += 1
            }
            
        }
        
        text = returnText
        return false
        
    }
    
}
