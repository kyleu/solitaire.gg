#!/usr/bin/env bash
echo "Starting Android build for Solitaire.gg"

if [ ! -d ../offline ]; then
  echo "Missing ../offline"
  exit
fi
cd "../offline"

pushd build/cordova
cordova build --release android
popd

mkdir -p bin/android
cp build/cordova/platforms/android/build/outputs/apk/*.apk bin/android

rm -f bin/android/Solitaire.gg.apk

jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore ../conf/certs/android-release-key.keystore bin/android/android-release-unsigned.apk solitaire.gg

zipalign -v 4 bin/android/android-release-unsigned.apk bin/android/Solitaire.gg.apk

echo "  Build complete!"
