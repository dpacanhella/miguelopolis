//
//  RSDevice.swift
//  Pods
//
//  Created by Andre M. Della Torre on 24/10/16.
//
//

import Foundation

public struct RSScreenSize {
    public static let SCREEN_WIDTH         = UIScreen.main.bounds.size.width
    public static let SCREEN_HEIGHT        = UIScreen.main.bounds.size.height
    public static let SCREEN_MAX_LENGTH    = max(RSScreenSize.SCREEN_WIDTH, RSScreenSize.SCREEN_HEIGHT)
    public static let SCREEN_MIN_LENGTH    = min(RSScreenSize.SCREEN_WIDTH, RSScreenSize.SCREEN_HEIGHT)
}

public struct RSDeviceType {
    public static let IS_IPHONE_4_OR_LESS  = UIDevice.current.userInterfaceIdiom == .phone && RSScreenSize.SCREEN_MAX_LENGTH < 568.0
    public static let IS_IPHONE_5          = UIDevice.current.userInterfaceIdiom == .phone && RSScreenSize.SCREEN_MAX_LENGTH == 568.0
    public static let IS_IPHONE_6          = UIDevice.current.userInterfaceIdiom == .phone && RSScreenSize.SCREEN_MAX_LENGTH == 667.0
    public static let IS_IPHONE_6P         = UIDevice.current.userInterfaceIdiom == .phone && RSScreenSize.SCREEN_MAX_LENGTH == 736.0
    public static let IS_IPAD              = UIDevice.current.userInterfaceIdiom == .pad && RSScreenSize.SCREEN_MAX_LENGTH == 1024.0
    public static let IS_IPAD_PRO          = UIDevice.current.userInterfaceIdiom == .pad && RSScreenSize.SCREEN_MAX_LENGTH == 1366.0
}
