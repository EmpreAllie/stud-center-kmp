import SwiftUI
import iosExport

class Colors {
    var black: SwiftUI.Color { fatalError("Must override") }
    var gray: SwiftUI.Color { fatalError("Must override") }
    var primary: SwiftUI.Color { fatalError("Must override") }
}

class LightColors: Colors {
    override var black: SwiftUI.Color { MultiplatformResource.colors.shared.black.getColor() }
    override var gray: SwiftUI.Color { MultiplatformResource.colors.shared.gray.getColor() }
    override var primary: SwiftUI.Color { MultiplatformResource.colors.shared.primaryColor.getColor() }
}

class DarkColors: Colors {
    override var black: SwiftUI.Color { MultiplatformResource.colors.shared.black.getColor() }
    override var gray: SwiftUI.Color { MultiplatformResource.colors.shared.gray.getColor() }
    override var primary: SwiftUI.Color { MultiplatformResource.colors.shared.primaryColor.getColor() }
}
