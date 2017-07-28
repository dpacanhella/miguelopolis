//
//  UIImage+Scale.swift
//  Pods
//
//  Created by Marcus Costa on 1/12/16.
//
//

import Foundation
import UIKit


public extension UIImage {
    
    public var height: Double { return Double(self.size.height) }
    public var width: Double { return Double(self.size.width) }
    
    public class func scaleImage(_ image: UIImage, size: CGSize) -> UIImage {
        UIGraphicsBeginImageContext(size)
        image.draw(in: CGRect(x: 0, y: 0, width: size.width, height: size.height))
        let scaledImage = UIGraphicsGetImageFromCurrentImageContext()
        UIGraphicsEndImageContext()
        
        return scaledImage!
    }
    
    public class func scaleImage(_ image: UIImage, maxWidth width: CGFloat, maxHeight height: CGFloat) -> UIImage {
        let oldWidth = image.size.width
        let oldHeight = image.size.height
        let scaleFactor = (oldWidth > oldHeight) ? width / oldWidth : height / oldHeight
        let newHeight = oldHeight * scaleFactor
        let newWidth = oldWidth * scaleFactor
        let newSize = CGSize(width: newWidth, height: newHeight)
        
        return scaleImage(image, size: newSize)
    }
    
    func scaleToFitInSize(_ size: CGSize) -> UIImage {
        
        var factor: Double = 1
        
        if self.height > size.height.doubleValue && self.width > size.width.doubleValue {
            factor = min(size.height.doubleValue / self.height, size.width.doubleValue / self.height)
        }
        else if self.height > size.height.doubleValue {
            factor = size.height.doubleValue / self.height
        }
        else if self.width > size.width.doubleValue {
            factor = size.width.doubleValue / self.width
        }
        else {
            return self
        }
        
        let newSize = CGSize(width: self.width * factor, height: self.height * factor)
        let newOrigin = CGPoint(x: (size.width - newSize.width) / 2.0, y: (size.height - newSize.height) / 2.0)
        let newRect = CGRect(origin: newOrigin, size: newSize)
        
        UIGraphicsBeginImageContextWithOptions(size, false, 0);
        self.draw(in: newRect)
        let newImage = UIGraphicsGetImageFromCurrentImageContext()
        UIGraphicsEndImageContext()
        
        return newImage!
    }

}
