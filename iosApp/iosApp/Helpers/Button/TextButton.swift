import SwiftUI

struct TextButton: View {
    var title: String
    var action: () -> Void
    var font: Font = Resource.typography().text.buttonText
    var foregroundColor: Color = .blue
    var backgroundColor: Color = .clear
    var padding: EdgeInsets = .init(top: 8, leading: 16, bottom: 8, trailing: 16)
    var cornerRadius: CGFloat = 8

    var body: some View {
        Button(action: action) {
            Text(title)
                .font(font)
                .foregroundColor(foregroundColor)
                .padding(padding)
                .background(backgroundColor)
                .clipShape(RoundedRectangle(cornerRadius: cornerRadius))
                .lineLimit(6)
                .multilineTextAlignment(.leading)
        }
    }
}

struct TextButton_Previews: PreviewProvider {
    static var previews: some View {
        TextButton(title: "Click Me", action: {
            print("Text Button Clicked")
        })
        .previewLayout(.sizeThatFits)
    }
}
