//
//  UIViewController+DeviceOrientation.swift
//  Pods
//
//  Created by Andre M. Della Torre on 2/1/16.
//
//

import Foundation

public struct RSDevice {
    
    public enum Orientation : Int {
        case unknown = 0, portrait, portraitUpsideUpDown, landscapeRight, landscapeLeft, cameraFacingDown, cameraFacingUp
        
        func description () -> String {
            switch self  {
            case .portrait:
                return "Portrait"
            case .portraitUpsideUpDown:
                return "Portrait Upside Down"
            case .landscapeRight:
                return "Landscape Right"
            case .landscapeLeft:
                return "Landscape Left"
            case .cameraFacingDown:
                return "Camera Facing Down"
            case .cameraFacingUp:
                return "Camera Facing Up"
            case .unknown:
                return "Unknown"
            }
        }
    }
    
    public static var lastOrientation = Orientation.unknown
    
    public static func isLandscape() -> Bool {
        return deviceOrientation() == .landscapeLeft || deviceOrientation() == .landscapeRight
    }
    
    public static func deviceOrientation() -> Orientation {
        
        let device = UIDevice.current
        
        if device.isGeneratingDeviceOrientationNotifications {
            device.beginGeneratingDeviceOrientationNotifications()
            
            return Orientation(rawValue: device.orientation.rawValue)!
        }
        
        return Orientation.unknown
    }
}
