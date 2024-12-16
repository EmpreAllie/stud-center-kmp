import SwiftUI
import iosExport

class Colors {
    var primary: SwiftUI.Color { fatalError("Must override") }
    var secondary: SwiftUI.Color { fatalError("Must override") }
    var error: SwiftUI.Color { fatalError("Must override") }
    var green: SwiftUI.Color { fatalError("Must override") }
    var freeTable: SwiftUI.Color { fatalError("Must override") }
    var fullTable: SwiftUI.Color { fatalError("Must override") }
    var white: SwiftUI.Color { fatalError("Must override") }
    var black: SwiftUI.Color { fatalError("Must override") }
    var gray: SwiftUI.Color { fatalError("Must override") }
    var transparent: SwiftUI.Color { fatalError("Must override") }
}

class LightColors: Colors {
    override var primary: SwiftUI.Color { MultiplatformResource.colors().primaryColor.getColor() }
    override var secondary: SwiftUI.Color { MultiplatformResource.colors().secondaryColor.getColor() }
    override var error: SwiftUI.Color { MultiplatformResource.colors().errorColor.getColor() }
    override var green: SwiftUI.Color { MultiplatformResource.colors().green.getColor() }
    override var freeTable: SwiftUI.Color { MultiplatformResource.colors().freeTable.getColor() }
    override var fullTable: SwiftUI.Color { MultiplatformResource.colors().fullTable.getColor() }
    override var white: SwiftUI.Color { MultiplatformResource.colors().white.getColor() }
    override var black: SwiftUI.Color { MultiplatformResource.colors().black.getColor() }
    override var gray: SwiftUI.Color { MultiplatformResource.colors().gray.getColor() }
    override var transparent: SwiftUI.Color { .clear }
}

class DarkColors: Colors {
    override var primary: SwiftUI.Color { MultiplatformResource.colors().primaryColor.getColor() }
    override var secondary: SwiftUI.Color { MultiplatformResource.colors().secondaryColor.getColor() }
    override var error: SwiftUI.Color { MultiplatformResource.colors().errorColor.getColor() }
    override var green: SwiftUI.Color { MultiplatformResource.colors().green.getColor() }
    override var freeTable: SwiftUI.Color { MultiplatformResource.colors().freeTable.getColor() }
    override var fullTable: SwiftUI.Color { MultiplatformResource.colors().fullTable.getColor() }
    override var white: SwiftUI.Color { MultiplatformResource.colors().white.getColor() }
    override var black: SwiftUI.Color { MultiplatformResource.colors().black.getColor() }
    override var gray: SwiftUI.Color { MultiplatformResource.colors().gray.getColor() }
    override var transparent: SwiftUI.Color { .clear }
}
