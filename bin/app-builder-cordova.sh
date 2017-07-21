#!/usr/bin/env bash
echo "  Building Cordova apps..."

[[ "$PWD" != */bin ]] && echo "Please run this from the bin directory."
[[ "$PWD" != */bin ]] && exit

cd ..

mkdir -p build/cordova
rm build/cordova/config.xml
cp "resources/offline/cordova/config.xml" "build/cordova/config.xml"
rm -rf build/cordova/www
cp -R "resources/offline/cordova/" "build/cordova"
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

rm -rf bin/ios
mkdir -p bin/ios
cp -R build/cordova/platforms/ios/build/emulator/Solitaire.gg.app bin/ios

rm -rf bin/android
mkdir -p bin/android
cp build/cordova/platforms/android/build/outputs/apk/*.apk bin/android

rm -rf bin/amazon-fireos
mkdir -p bin/amazon-fireos
cp build/cordova/platforms/amazon-fireos/out/*.apk bin/amazon-fireos

rm -rf bin/firefoxos
mkdir -p bin/firefoxos
cp build/cordova/platforms/firefoxos/build/package.zip bin/firefoxos/Solitaire.gg.zip
