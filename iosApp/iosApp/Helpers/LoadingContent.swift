import SwiftUI

struct LoadingContent: View {
    var body: some View {
        ZStack(alignment: .center) {
            CircleProgressView()
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(Resource.colors().black.opacity(0.8))
    }
}

#Preview {
    LoadingContent()
}
