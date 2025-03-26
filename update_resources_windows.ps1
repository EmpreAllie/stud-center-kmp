$COLORS_SOURCE_FILE = "shared\resources\src\commonMain\moko-resources\colors\colors.xml"
$STRINGS_SOURCE_FILE = "shared\resources\src\commonMain\moko-resources\base\strings.xml"

$COLORS_TARGET_FILE = "androidApp\ui\src\main\res\values\colors.xml"
$COLORS_KT_FILE = "androidApp\ui\src\main\java\com\studcenter\ui\Colors.kt"
$COLORS_SWIFT_FILE = "iosApp\iosApp\Resources\Colors.swift"

$BUILD_DIR = "shared\resources\build"

$LANGUAGE_COUNT = Read-Host "Сколько у вас языков локализации в приложении? (ответ 1 цифра)"
if ($LANGUAGE_COUNT -eq 0) {
    Write-Host "Локализация не будет выполнена, так как вы указали 0 языков."
    exit 0
}

$LANGUAGES = Read-Host "Какие языки? (введите языковые коды через запятую без пробелов, например: en,ru,fr)"
$LANGUAGE_CODES = $LANGUAGES -split ','

$STRINGS_TARGET_PATHS = @("androidApp\ui\src\main\res\values\strings.xml")
foreach ($LANG_CODE in $LANGUAGE_CODES) {
    $STRINGS_TARGET_PATHS += "shared\resources\src\commonMain\moko-resources\$LANG_CODE\strings.xml"
}

function Copy-ColorsFile {
    Copy-Item -Path $COLORS_SOURCE_FILE -Destination $COLORS_TARGET_FILE -Force
    Write-Host "Файл $COLORS_SOURCE_FILE успешно скопирован в $COLORS_TARGET_FILE"
}

function Update-ColorsKt {
    $NEW_COLOR_NAME = Select-String -Path $COLORS_SOURCE_FILE -Pattern 'name="([^"]*)"' | ForEach-Object { $_.Matches.Groups[1].Value } | Select-Object -Last 1

    if (-not $NEW_COLOR_NAME) {
        Write-Host "Не удалось найти новое имя цвета в $COLORS_SOURCE_FILE"
        return
    }

    if (Select-String -Path $COLORS_KT_FILE -Pattern "val $NEW_COLOR_NAME:") {
        Write-Host "Цвет $NEW_COLOR_NAME уже существует в $COLORS_KT_FILE"
        return
    }

    (Get-Content $COLORS_KT_FILE) -replace "abstract val transparent: Color", "abstract val transparent: Color`r`n    abstract val $NEW_COLOR_NAME: Color" | Set-Content $COLORS_KT_FILE
    (Get-Content $COLORS_KT_FILE) -replace "override val transparent: Color = Color.Transparent,", "override val transparent: Color = Color.Transparent,`r`n        override val $NEW_COLOR_NAME: Color = Color(MultiplatformResource.colors.$NEW_COLOR_NAME.getColor(context))," | Set-Content $COLORS_KT_FILE

    Write-Host "Цвет $NEW_COLOR_NAME успешно добавлен в $COLORS_KT_FILE"
}

function Copy-StringsFile {
    foreach ($TARGET_FILE in $STRINGS_TARGET_PATHS) {
        $TARGET_DIR = Split-Path -Parent $TARGET_FILE
        if (-not (Test-Path $TARGET_DIR)) {
            New-Item -ItemType Directory -Path $TARGET_DIR -Force | Out-Null
        }
        Copy-Item -Path $STRINGS_SOURCE_FILE -Destination $TARGET_FILE -Force
        Write-Host "Файл успешно скопирован в $TARGET_FILE"
    }
}

function Clean-Build {
    if (Test-Path $BUILD_DIR) {
        Remove-Item -Recurse -Force $BUILD_DIR
        Write-Host "Папка build успешно удалена."
    }
}

function Rebuild-Module {
    Write-Host "Пересборка модуля resource..."
    & ./gradlew :shared:resources:build
    if ($?) {
        Write-Host "Модуль resource успешно пересобран."
    } else {
        Write-Host "Ошибка при пересборке модуля resource."
    }
}

while ($true) {
    Start-Sleep -Seconds 5
    if (Test-Path $COLORS_SOURCE_FILE) {
        Write-Host "Файл $COLORS_SOURCE_FILE изменён. Выполняется обновление..."
        Copy-ColorsFile
        Update-ColorsKt
    }

    if (Test-Path $STRINGS_SOURCE_FILE) {
        Write-Host "Файл $STRINGS_SOURCE_FILE изменён. Выполняется копирование..."
        Copy-StringsFile
    }

    Clean-Build
    Rebuild-Module
}
