import iosExport
import SwiftUI

struct ImageButton: View {
    var image: Image
    var action: () -> Void
    var backgroundColor: SwiftUI.Color = Resource.colors().primary
    var iconColor: SwiftUI.Color = Resource.colors().secondary
    var size: Double? = nil
    var padding: Double = 8
    var cornerRadius: Double = 100
    var body: some View {
            Button(action: action) {
                image
                    .resizable()
                    .foregroundStyle(iconColor)
                    .scaledToFit()
                    .padding(padding)
            }
            .background(backgroundColor)
            .clipShape(RoundedRectangle(cornerRadius: cornerRadius))
            .frame(width: size ?? 44, height: size ?? 44)
    }

    func setBackground(_ color: SwiftUI.Color) -> ImageButton {
        return ImageButton(image: image, action: action, backgroundColor: color, iconColor: iconColor)
    }

    func setIconColor(_ color: SwiftUI.Color) -> ImageButton {
        return ImageButton(image: image, action: action, backgroundColor: backgroundColor, iconColor: color)
    }
}

#Preview {
    ImageButton(image: MultiplatformResource.images().ic_back.toImage()) {}
}
