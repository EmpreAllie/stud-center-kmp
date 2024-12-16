import iosExport
import SwiftUI

struct DialogError: View {
    @Binding var title: String
    @Binding var description: String
    @Binding var buttonText: String
    var onClose: () -> Void

    @State private var showContent = false

    var body: some View {
        VStack(spacing: 0) {
            HStack(spacing: 0) {
                Text(title)
                     .font(Resource.typography().text.title)
                     .foregroundColor(MultiplatformResource.colors.shared.secondaryColor.getColor())
                     .padding(.bottom, 12)
                Spacer()
            }
            
            HStack(spacing: 0) {
                Text($description.wrappedValue)
                    .font(Resource.typography().text.main)
                    .foregroundColor(.black.opacity(0.5))
                    .padding(.bottom, 24)
                Spacer()
            }

            HStack(spacing: 0) {

                MainButton(title: buttonText,
                           background: Resource.colors().white,
                           contentColor: Resource.colors().black,
                           isEnabled: .constant(true), isLoading: .constant(false), action: onClose)
            }
        }
        .padding(EdgeInsets(top: 16, leading: 24, bottom: 16, trailing: 24))
        .frame(maxWidth: .infinity)
        .background(Resource.colors().white)
        .cornerRadius(16)
        .shadow(radius: 2)
    }
}

struct DialogError_Previews: PreviewProvider {
    static var previews: some View {
        DialogError(title: .constant("Ошибка"), description: .constant("Что-то пошло не так.Что-то пошло не так.Что-то пошло не так."), buttonText: .constant("Закрыть")) {}
            .frame(width: 300)
    }
}
