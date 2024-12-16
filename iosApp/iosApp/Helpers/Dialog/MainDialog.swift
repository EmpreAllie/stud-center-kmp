import iosExport
import SwiftUI

struct MainDialog: View {
    @Binding var title: String
    @Binding var description: String
    @Binding var confirmText: String
    var confirmAction: () -> Void
    var onClose: () -> Void

    @State private var showContent = false

    var body: some View {
        VStack(spacing: 0) {
            HStack {
                Spacer()
                ImageButton(image: MultiplatformResource.images().ic_cross.toImage(), action: onClose, backgroundColor: Resource.colors().white)
                    .padding(EdgeInsets(top: 0, leading: 0, bottom: 16, trailing: 24))
            }
            VStack(spacing: 0) {
                HStack(spacing: 0) {
                    Text(title)
                        .font(Resource.typography().text.title)
                        .foregroundColor(Resource.colors().black)
                        .padding(.bottom, 12)
                    Spacer()
                }
                
                HStack(spacing: 0) {
                    Text($description.wrappedValue)
                        .font(Resource.typography().text.main)
                        .foregroundColor(Resource.colors().black)
                        .padding(.bottom, 24)
                    Spacer()
                }
                
                HStack(spacing: 0) {
                    MainButton(title: confirmText,
                               background: Resource.colors().white,
                               contentColor: Resource.colors().black,
                               isEnabled: .constant(true), isLoading: .constant(false), action: onClose)
                }
            }
            .padding(EdgeInsets(top: 16, leading: 24, bottom: 16, trailing: 24))
            .frame(maxWidth: .infinity)
            .background(Resource.colors().gray)
            .cornerRadius(16)
            .shadow(radius: 2)
            .padding(EdgeInsets(top: 0, leading: 24, bottom: 0, trailing: 24))
        }
    }
}

struct MainDialog_Previews: PreviewProvider {
    static var previews: some View {
        MainDialog(title: .constant("123"), description: .constant("123 1231 23"), confirmText: .constant("CONTINUE"), confirmAction: {}, onClose: {})
    }
}
