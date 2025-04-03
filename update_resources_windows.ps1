$COLORS_SOURCE_FILE = "shared\resources\src\commonMain\moko-resources\colors\colors.xml"
$STRINGS_SOURCE_FILE = "shared\resources\src\commonMain\moko-resources\base\strings.xml"

$COLORS_TARGET_FILE = "androidApp\ui\src\main\res\values\colors.xml"
$COLORS_KT_FILE = "androidApp\ui\src\main\java\com\studcenter\ui\Colors.kt"
$COLORS_SWIFT_FILE = "iosApp\iosApp\Resources\Colors.swift"

$BUILD_DIR = "shared\resources\build"

$LANGUAGE_COUNT = Read-Host "How many languages does the app use? (the answer is 1 number)"
if ($LANGUAGE_COUNT -eq 0) {
    Write-Host "The localization won't be done because you've specified 0 languages."
    exit 0
}

$LANGUAGES = Read-Host "What are the languages? (enter language codes with commas and without spaces, for example: en,ru,fr)"
$LANGUAGE_CODES = $LANGUAGES -split ','

$STRINGS_TARGET_PATHS = @("androidApp\ui\src\main\res\values\strings.xml")
foreach ($LANG_CODE in $LANGUAGE_CODES) {
    $STRINGS_TARGET_PATHS += "shared\resources\src\commonMain\moko-resources\$LANG_CODE\strings.xml"
}

function Copy-ColorsFile {
    Copy-Item -Path $COLORS_SOURCE_FILE -Destination $COLORS_TARGET_FILE -Force
    Write-Host "File $COLORS_SOURCE_FILE copied successfully to $COLORS_TARGET_FILE"
}

function Update-ColorsKt {
    $NEW_COLOR_NAME = Select-String -Path $COLORS_SOURCE_FILE -Pattern 'name="([^"]*)"' | ForEach-Object { $_.Matches.Groups[1].Value } | Select-Object -Last 1

    if (-not $NEW_COLOR_NAME) {
        Write-Host "Unable to find a new color name in $COLORS_SOURCE_FILE"
        return
    }

    if (Select-String -Path $COLORS_KT_FILE -Pattern "val ${NEW_COLOR_NAME}:") {
        Write-Host "Color $NEW_COLOR_NAME already exists in $COLORS_KT_FILE"
        return
    }

    (Get-Content $COLORS_KT_FILE) -replace "abstract val transparent: Color", "abstract val transparent: Color`r`n    abstract val ${NEW_COLOR_NAME}: Color" | Set-Content $COLORS_KT_FILE
    (Get-Content $COLORS_KT_FILE) -replace "override val transparent: Color = Color.Transparent,", "override val transparent: Color = Color.Transparent,`r`n        override val ${NEW_COLOR_NAME}: Color = Color(MultiplatformResource.colors.$NEW_COLOR_NAME.getColor(context))," | Set-Content $COLORS_KT_FILE

    Write-Host "Color $NEW_COLOR_NAME added successfully to $COLORS_KT_FILE"
}

function Copy-StringsFile {
    foreach ($TARGET_FILE in $STRINGS_TARGET_PATHS) {
        $TARGET_DIR = Split-Path -Parent $TARGET_FILE
        if (-not (Test-Path $TARGET_DIR)) {
            New-Item -ItemType Directory -Path $TARGET_DIR -Force | Out-Null
        }
        Copy-Item -Path $STRINGS_SOURCE_FILE -Destination $TARGET_FILE -Force
        Write-Host "File copied successfully to $TARGET_FILE"
    }
}

function Clean-Build {
    if (Test-Path $BUILD_DIR) {
        Remove-Item -Recurse -Force $BUILD_DIR
        Write-Host "Build folder deleted successfully."
    }
}

function Rebuild-Module {
    Write-Host "Rebuilding module: resource..."
    & ./gradlew :shared:resources:build
    if ($?) {
        Write-Host "Resource module rebuilt successfully."
    } else {
        Write-Host "Error while rebuilding resource module."
    }
}

while ($true) {
    Start-Sleep -Seconds 5
    if (Test-Path $COLORS_SOURCE_FILE) {
        Write-Host "File $COLORS_SOURCE_FILE was changed. Updating..."
        Copy-ColorsFile
        Update-ColorsKt
    }

    if (Test-Path $STRINGS_SOURCE_FILE) {
        Write-Host "File $STRINGS_SOURCE_FILE was changed. Copying..."
        Copy-StringsFile
    }

    Clean-Build
    Rebuild-Module
}
