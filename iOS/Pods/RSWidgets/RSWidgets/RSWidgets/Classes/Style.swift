//
//  Style.swift
//  Pods
//
//  Created by Marcus Costa on 2/1/16.
//
//

import UIKit

open class Style {
    
    open var navigationBarColor: UIColor = UIColor.blue
    open var navigationBarTextColor: UIColor = UIColor.white
    open var navigationBarTintColor:UIColor = UIColor.white

    open var colors = [UIColor]()
    open var images = [UIImage]()
    
    fileprivate var mapperColor = [String: Int]()
    fileprivate var mapperImages = [String: Int]()
    
    open func colorByTag(_ tag: String) -> UIColor? {
        if let index = mapperColor[tag] {
            return colors[index]
        }
        return nil
    }
    
    open func imageByTag(_ tag: String) -> UIImage? {
        if let index = mapperImages[tag] {
            return images[index]
        }
        return nil
    }
    
    public init() {
        
    }
    
    public convenience init(withBuilder builder: Style.Builder) {
        self.init()
        
        if builder.navigationBarColor != nil {
            navigationBarColor = builder.navigationBarColor!
        }
        
        if builder.navigationBarTextColor != nil {
            navigationBarTextColor = builder.navigationBarTextColor!
        }
        
        if builder.navigationBarTintColor != nil {
            navigationBarTintColor = builder.navigationBarTintColor!
        }
        
        colors = builder.colors
        mapperColor = builder.mapperColor
        images = builder.images
        mapperImages = builder.mapperImages
    }
}

// MARK: - Equatable
extension Style: Equatable {}
public func ==(lhs: Style, rhs: Style) -> Bool {
    return  lhs.navigationBarColor == rhs.navigationBarColor &&
            lhs.navigationBarTextColor == rhs.navigationBarTextColor &&
            lhs.navigationBarTintColor == rhs.navigationBarTintColor &&
            lhs.colors == rhs.colors &&
            lhs.images == rhs.images
}

// MARK: - CustomStringConvertible
extension Style: CustomStringConvertible {
    
    public var description: String {
        
        return "{Style:\n" +
                "NavigationBarColor: \(navigationBarColor)\n" +
                "NavigationBarTextColor: \(navigationBarTextColor)\n" +
                "NavigationBarTintColor: \(navigationBarTintColor)\n" +
                "Background Mapper Colors: [\n" +
                "\(mapperColor)\n]" +
                "Images Mapper: [\n" +
                "\(mapperImages)\n]" +
                "}"
    }
    
}

// MARK: - Style Builder
extension Style {
    open class Builder {
        var navigationBarColor: UIColor?
        var navigationBarTextColor: UIColor?
        var navigationBarTintColor:UIColor?
        
        var colors = [UIColor]()
        var images = [UIImage]()
        
        var mapperColor = [String: Int]()
        var mapperImages = [String: Int]()
        
        public init() {}

        open func build() -> Style {
            return Style(withBuilder: self)
        }
        
        open func setNavigationBarColor(_ color: UIColor) -> Builder {
            navigationBarColor = color
            return self
        }
        
        open func setNavigationBarTextColor(_ color: UIColor) -> Builder {
            navigationBarTextColor = color
            return self
        }
        
        open func setNavigationBarTintColor(_ color: UIColor) -> Builder {
            navigationBarTintColor = color
            return self
        }
        
        open func addColor(_ color: UIColor, withTag tag: String) -> Builder {
            mapperColor[tag] = colors.count
            colors.append(color)
            return self
        }
        
        open func addImage(_ image: UIImage, withTag tag: String) -> Builder {
            mapperImages[tag] = images.count
            images.append(image)
            return self
        }
    }
}

