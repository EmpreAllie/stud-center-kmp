import SwiftUI

struct InputTextField: View {
    @State private var isFocused: Bool = false
    @State private var textFieldValue: String

    var text: String = ""
    var hintText: String
    var singleLine: Bool = false
    var height: CGFloat = 56
    var textSelection: Int = 0
    var errorText: String? = nil
    var isError: Bool = false
    var isEnabled: Bool = true
    var keyboardType: UIKeyboardType = .default
    var maxLength: Int = Int.max
    var maxLines: Int = Int.max
    var onTextChange: (String) -> Void = { _ in }
    var icon: String? = nil
    var onClickIcon: () -> Void = {}

    init(
        text: String = "",
        hintText: String,
        singleLine: Bool = false,
        height: CGFloat = 56,
        textSelection: Int = 0,
        errorText: String? = nil,
        isError: Bool = false,
        isEnabled: Bool = true,
        keyboardType: UIKeyboardType = .default,
        maxLength: Int = Int.max,
        maxLines: Int = Int.max,
        onTextChange: @escaping (String) -> Void = { _ in },
        icon: String? = nil,
        onClickIcon: @escaping () -> Void = {}
    ) {
        self.textFieldValue = text
        self.hintText = hintText
        self.onTextChange = onTextChange
        self.singleLine = singleLine
        self.height = height
        self.textSelection = textSelection
        self.errorText = errorText
        self.isError = isError
        self.isEnabled = isEnabled
        self.keyboardType = keyboardType
        self.maxLength = maxLength
        self.maxLines = maxLines
        self.icon = icon
        self.onClickIcon = onClickIcon
    }

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            ZStack(alignment: .leading) {
                if textFieldValue.isEmpty {
                    Text(hintText)
                        .foregroundColor((isError || errorText != nil ? Resource.colors().error : Resource.colors().black).opacity(0.4))
                        .font(Resource.typography().text.hintText)
                        .padding(.leading, 8)
                }
                HStack {
                    TextField("", text: $textFieldValue, onEditingChanged: { isEditing in
                        if !isEnabled {
                            isFocused = false
                        } else {
                            isFocused = isEditing
                        }
                    })
                    .disabled(!isEnabled)
                    .frame(maxHeight: .infinity)
                    .padding(.horizontal, icon != nil ? 16 : 8)
                    .foregroundColor(Resource.colors().secondary)
                    .font(Resource.typography().text.inputText)
                    .keyboardType(keyboardType)
                    .onChange(of: textFieldValue) { newValue in
                        if newValue.count <= maxLength {
                            onTextChange(newValue)
                        } else {
                            textFieldValue = String(newValue.prefix(maxLength))
                        }
                    }

                    if let icon = icon {
                        Image(systemName: icon)
                            .resizable()
                            .frame(width: 24, height: 24)
                            .foregroundColor(Resource.colors().secondary)
                            .onTapGesture {
                                onClickIcon()
                            }
                    }
                }
            }
            .padding(.vertical, 4)
            .padding(.horizontal, 16)
            .frame(height: height)
            .background(Resource.colors().white)
            .cornerRadius(5)
            .overlay(
                RoundedRectangle(cornerRadius: 5)
                    .stroke(
                        isError || errorText != nil ? Resource.colors().error : (isFocused ? Resource.colors().primary : Resource.colors().white),
                        lineWidth: 3
                    )
            )

            if let errorText = errorText {
                Text(errorText)
                    .foregroundColor(Resource.colors().error)
                    .font(Resource.typography().text.inputText)
                    .padding(.horizontal, 20)
            }
        }
    }
}

struct InputTextField_Previews: PreviewProvider {
    static var previews: some View {
        VStack {
            Spacer()
            InputTextField(
                hintText: "Пароль",
                singleLine: true,
                errorText: nil,
                isError: false
            )
            .padding()
            Spacer()
        }
        .background(Resource.colors().black)
    }
}
