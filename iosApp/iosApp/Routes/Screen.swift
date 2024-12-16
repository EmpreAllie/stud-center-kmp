import SwiftUI
import iosExport

extension iosExport.Screen {
    @ViewBuilder
    func view() -> some View {
        
        switch self {
        case iosExport.Screen.splash:
            ZStack {} //SplashScreen()
        case iosExport.Screen.authorization:
            ZStack {} //  AuthorizationScreen()
        case iosExport.Screen.display:
            ZStack {} //  AuthorizationScreen()
        case iosExport.Screen.menu:
            ZStack {} //MainScreen()
        case iosExport.Screen.record:
            ZStack {} //  AuthorizationScreen()
        case iosExport.Screen.role:
            ZStack {} //  AuthorizationScreen()
        default:
            ZStack {} //AuthorizationScreen()
        }
    }
}
