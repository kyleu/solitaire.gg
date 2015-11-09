#!/usr/bin/env bash
echo "Building Solitaire.gg"

if [ ! -d ../offline ]; then
  echo "Missing ../offline"
  exit
fi
cd "../offline"

_now=$(date +"%Y-%d-%m")
mkdir -p "build"
printf "Solitaire.GG\\n  Build Generated: $_now" > "build/build-info"

rm -rf bin

echo "  Updating web assets..."

mkdir -p build/web/assets/images
mkdir -p build/web/assets/stylesheets
mkdir -p build/web/assets/javascripts
mkdir -p build/web/assets/javascripts/lib

cp -R "../public/images/" "build/web/assets/images"

cp -R "build/templates/" "build/web/"

cp "../target/web/rjs/build/javascripts/main.js" "build/web/assets/javascripts/main.js"
cp "../target/web/rjs/build/client-opt.js" "build/web/assets/client-opt.js"
cp "../target/web/less/main/stylesheets/solitaire.gg.min.css" "build/web/assets/stylesheets"

mkdir -p "build/web/assets/lib/phaser/js"
cp "../public/lib/phaser/js/phaser-arcade-physics.min.js" "build/web/assets/lib/phaser/js"

mkdir -p "build/web/assets/lib/underscorejs"
cp "../target/web/public/main/lib/underscorejs/underscore-min.js" "build/web/assets/lib/underscorejs"

mkdir -p "build/web/assets/lib/requirejs"
cp "../target/web/rjs/build/lib/requirejs/require.min.js" "build/web/assets/lib/requirejs"

echo "  Building Electron apps..."
mkdir bin
cp -R "assets/electron/" "bin"

mkdir -p build/electron
cp -R "build/web/" "build/electron"
cp -R "src/electron/" "build/electron"
asar pack build/electron build/solitaire.gg.asar

cp build/solitaire.gg.asar bin/osx/Solitaire.gg.app/Contents/Resources/app.asar
cp build/solitaire.gg.asar bin/linux/resources/app.asar
cp build/solitaire.gg.asar bin/win32/resources/app.asar
cp build/solitaire.gg.asar bin/win64/resources/app.asar

echo "  Building Cordova apps..."

mkdir -p build/cordova
rm build/cordova/config.xml
cp "src/cordova/config.xml" "build/cordova/config.xml"
rm -rf build/cordova/www
cp -R "assets/cordova/" "build/cordova"
cp "src/cordova/config.xml" "build/cordova"
cp -R "build/web/" "build/cordova/www"
cp -R "src/cordova/www/" "build/cordova/www"

rm -rf build/cordova/res

mkdir -p build/cordova/res/ios
cp -R "assets/design/ios/" "build/cordova/res/ios/"

mkdir -p build/cordova/res/android
cp -R "assets/design/android/" "build/cordova/res/android/"

mkdir -p build/cordova/res/firefoxos
cp -R "assets/design/firefoxos/" "build/cordova/res/firefoxos/"

pushd build/cordova
cordova build
popd

mkdir -p bin/ios
cp -R build/cordova/platforms/ios/build/emulator/Solitaire.gg.app bin/ios

mkdir -p bin/android
cp build/cordova/platforms/android/build/outputs/apk/*.apk bin/android

mkdir -p bin/amazon-fireos
cp build/cordova/platforms/amazon-fireos/out/*.apk bin/amazon-fireos

mkdir -p bin/firefoxos
cp build/cordova/platforms/firefoxos/build/package.zip bin/firefoxos/Solitaire.gg.zip

echo "  Build complete!"
