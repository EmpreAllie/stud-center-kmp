import SwiftUI

struct CircleProgressView: View {
    @State private var isAnimating: Bool = false
    private var color: SwiftUI.Color

    init(color: SwiftUI.Color = Resource.colors().primary) {
        self.color = color
    }

    var body: some View {
        VStack {
            Circle()
                .trim(from: 0, to: 0.7)
                .stroke(color, style: StrokeStyle(lineWidth: 5, lineCap: .round))
                .frame(width: 30, height: 30)
                .rotationEffect(Angle(degrees: isAnimating ? 360 : 0))
                .animation(Animation.linear(duration: 0.8).repeatForever(autoreverses: false), value: UUID())
                .onAppear {
                    isAnimating = true
                }
        }
    }
}

#Preview {
    CircleProgressView(color: Color.blue)
}
