import iosExport
import SwiftUI

struct MainButton: View {
    var title: String
    var background: SwiftUI.Color = MultiplatformResource.colors.shared.primaryColor.getColor()
    var contentColor: SwiftUI.Color = MultiplatformResource.colors.shared.secondaryColor.getColor()
    @Binding var isEnabled: Bool
    @Binding var isLoading: Bool
    var action: () -> Void

    var body: some View {
        Button(action: action) {
            ZStack {
                RoundedRectangle(cornerRadius: 8)
                    .fill(background)
                    .frame(height: 52)

                if !isLoading {
                    Text(title)
                        .font(Resource.typography().text.buttonText)
                        .fontWeight(Font.Weight.bold)
                        .foregroundColor(contentColor)
                        .padding()
                        .lineLimit(nil)
                        .fixedSize(horizontal: false, vertical: true)
                        .multilineTextAlignment(.center)
                } else {
                    ProgressView()
                        .progressViewStyle(CircularProgressViewStyle(tint: contentColor))
                        .scaleEffect(1.5)
                }
            }
            .animation(.easeInOut, value: isLoading)
        }
        .disabled(isLoading || !isEnabled)
        .buttonStyle(PlainButtonStyle())
        .animation(.easeInOut, value: isLoading)
    }
}

struct MainButton_Previews: PreviewProvider {
    static var previews: some View {
        MainButton(
            title: MultiplatformResource.strings().accept.localize(),
            background: Resource.colors().white,
            contentColor: Resource.colors().black,
            isEnabled: .constant(true),
            isLoading: .constant(false),
            action: { print("Button tapped") }
        )
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(Resource.colors().black)
        .padding()
        .previewLayout(.sizeThatFits)
    }
}
