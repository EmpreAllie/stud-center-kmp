import iosExport
import SwiftUI

struct Typography {
    var text: TitleTypography { TitleTypography() }
    var main: MainTypography { MainTypography() }
    var menu: MenuTypography { MenuTypography() }
    var navigation: NavigationTypography { NavigationTypography() }
}

struct TitleTypography {
    var title: Font { .bold(size: 22) }
    var main: Font { .regular(size: 16) }
    var buttonText: Font { .bold(size: 18) }
    var inputText: Font { .regular(size: 16) }
    var hintText: Font { .regular(size: 16) }
}

struct MainTypography {
    var bigText: Font { .bold(size: 40) }
    var mediumText: Font { .bold(size: 32) }
    var text: Font { .bold(size: 24) }
    var button: Font { .regular(size: 20) }
}

struct MenuTypography {
    var title: Font { .regular(size: 24) }
    var tableDescription: Font { .regular(size: 16) }
    var itemKey: Font { .bold(size: 24) }
    var itemValue: Font { .regular(size: 24) }
}

struct NavigationTypography {
    var title: Font { .bold(size: 28) }
    var button: Font { .regular(size: 20) }
}
