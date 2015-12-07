#!/usr/bin/env bash
echo "  Signing Mac App Store app..."

cd ../offline

APP="Solitaire.gg"
APP_PATH="bin/osx/$APP.app"

RESULT_PATH="bin/osx/$APP.pkg"

APP_KEY="3rd Party Mac Developer Application: Kyle Unverferth (C6S478FYLD)"
INSTALLER_KEY="3rd Party Mac Developer Installer: Kyle Unverferth (C6S478FYLD)"

FRAMEWORKS_PATH="$APP_PATH/Contents/Frameworks"

codesign --deep -fs "$APP_KEY" --entitlements assets/mac-app-store/child.plist "$FRAMEWORKS_PATH/Electron Framework.framework/Libraries/libnode.dylib"
codesign --deep -fs "$APP_KEY" --entitlements assets/mac-app-store/child.plist "$FRAMEWORKS_PATH/Electron Framework.framework/Electron Framework"
codesign --deep -fs "$APP_KEY" --entitlements assets/mac-app-store/child.plist "$FRAMEWORKS_PATH/Electron Framework.framework/"
codesign --deep -fs "$APP_KEY" --entitlements assets/mac-app-store/child.plist "$FRAMEWORKS_PATH/Electron Helper.app/"
codesign --deep -fs "$APP_KEY" --entitlements assets/mac-app-store/child.plist "$FRAMEWORKS_PATH/Electron Helper EH.app/"
codesign --deep -fs "$APP_KEY" --entitlements assets/mac-app-store/child.plist "$FRAMEWORKS_PATH/Electron Helper NP.app/"
codesign  -fs "$APP_KEY" --entitlements assets/mac-app-store/parent.plist "$APP_PATH"
productbuild --component "$APP_PATH" /Applications --sign "$INSTALLER_KEY" "$RESULT_PATH"
