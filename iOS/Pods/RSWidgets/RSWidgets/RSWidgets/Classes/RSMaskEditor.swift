//
//  RSMaskEditor.swift
//  Pods
//
//  Created by Andre M. Della Torre on 8/3/16.
//
//

import Foundation
import UIKit

class RSMaskEditor {
    
    class func shouldChangeCharactersInRange(_ range: NSRange, replacementString string: String, textField: UITextField, mask: String) -> Bool {
        
        let digits = CharacterSet.decimalDigits
        let letters = CharacterSet.letters
        
        var currentTextDigited = (textField.text! as NSString).replacingCharacters(in: range, with: string)
        
        if string.characters.count == 0 {
            
            var lastCharIdx = currentTextDigited.endIndex != currentTextDigited.startIndex ? currentTextDigited.characters.index(before: currentTextDigited.endIndex) : currentTextDigited.endIndex
            
            let localRange = Range(uncheckedBounds: (lower: lastCharIdx, upper: lastCharIdx))
            while currentTextDigited.characters.count > 0 && !digits.isSuperset(of: CharacterSet(charactersIn:currentTextDigited[localRange])) {
                
                currentTextDigited = currentTextDigited[Range(currentTextDigited.startIndex..<lastCharIdx)]
                
                if currentTextDigited.characters.count > 0 {
                    lastCharIdx = currentTextDigited.characters.index(before: currentTextDigited.endIndex)
                }
            }
            textField.text = currentTextDigited;
            return false;
        }
        
        if (currentTextDigited.characters.count > mask.characters.count) {
            return false;
        }
        
        var returnText = String()
        
        var lastIdx = 0
        var needAppend = false
        
        for (idx, character) in currentTextDigited.characters.enumerated() {
            let localRange = Range(uncheckedBounds: (lower: mask.characters.index(mask.startIndex, offsetBy: idx), upper: mask.characters.index(mask.startIndex, offsetBy: idx + 1 )))
            let characterMask = mask[localRange]
            
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
                if digits.isSuperset(of: CharacterSet(charactersIn:charString)) && charString != characterMask {
                    needAppend = true
                }
                returnText.append(characterMask)
            }
            lastIdx = idx
        }
        
        for idx in (lastIdx + 1)..<mask.characters.count {
            let localRange = Range(uncheckedBounds: (lower: mask.characters.index(mask.startIndex, offsetBy: idx), upper: mask.characters.index(mask.startIndex, offsetBy: idx)))
            let characterMask = mask[localRange]
            
            if String(characterMask) != "#" && String(characterMask) != "@" {
                returnText.append(characterMask)
            }
            if String(characterMask) == "#" || String(characterMask) == "@" {
                break
            }
        }
        
        if needAppend {
            returnText.append(string)
        }
        
        textField.text =  returnText
        
        return false
    }
    
}
