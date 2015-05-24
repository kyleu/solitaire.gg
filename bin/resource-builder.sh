#!/usr/bin/env bash
echo "Building Solitaire.gg design resources..."

if [ ! -d ../offline/resources/design ]; then
  echo "Missing ../offline/resources/design"
  exit
fi
cd "../offline/resources/design"

rm -rf osx
mkdir osx

convert original/icon.png -resize 1024x1024 osx/icon_512x512@2x.png
convert original/icon.png -resize 512x512 osx/icon_512x512.png
convert original/icon.png -resize 512x512 osx/icon_256x256@2x.png
convert original/icon.png -resize 256x256 osx/icon_256x256.png
convert original/icon.png -resize 256x256 osx/icon_128x128@2x.png
convert original/icon.png -resize 128x128 osx/icon_128x128.png
convert original/icon.png -resize 64x64 osx/icon._32x32@2x.png
convert original/icon.png -resize 32x32 osx/icon_32x32.png
convert original/icon.png -resize 32x32 osx/icon_16x16@2x.png
convert original/icon.png -resize 16x16 osx/icon_16x16.png

rm -rf windows
mkdir windows

convert original/icon.png -resize 16x16 windows/icon_16x16.png
convert original/icon.png -resize 32x32 windows/icon_32x32.png
convert original/icon.png -resize 48x48 windows/icon_48x48.png
convert original/icon.png -resize 256x256 windows/icon_256x256.png

rm -rf ios
mkdir ios

convert original/icon.png -resize 512x512 ios/icon.png
convert original/splash-portrait.png -resize 320x480 ios/splash-iphone.png
convert original/splash-portrait.png -resize 640x960 ios/splash-iphone-retina.png
convert original/splash-portrait.png -resize 640x1136 ios/splash-iphone-5.png

convert original/splash-portrait.png -resize 750x1334 ios/splash-iphone6.png
convert original/splash-portrait.png -resize 1242x2208 ios/splash-iphone6-plus-portrait.png
convert original/splash-landscape.png -resize 2208x1242 ios/splash-iphone6-plus-landscape.png

convert original/splash-portrait.png -resize 768x1024 ios/splash-ipad-portrait.png
convert original/splash-portrait.png -resize 1536x2048 ios/splash-ipad-portrait-retina.png
convert original/splash-landscape.png -resize 1024x768 ios/splash-ipad-landscape.png
convert original/splash-landscape.png -resize 2048x1496 ios/splash-ipad-landscape-retina.png

rm -rf android
mkdir android

convert original/icon.png -resize 36x36 android/icon-ldpi.png
convert original/icon.png -resize 48x48 android/icon-mdpi.png
convert original/icon.png -resize 72x72 android/icon-hdpi.png
convert original/icon.png -resize 96x96 android/icon-xdpi.png
convert original/icon.png -resize 144x144 android/icon-xxhdpi.png
convert original/icon.png -resize 192x192 android/icon-xxxhdpi.png

rm -rf pokki
mkdir pokki

convert original/icon.png -resize 19x19 pokki/icon-small.png
convert original/icon.png -resize 29x29 pokki/icon-medium.png
convert original/icon.png -resize 42x42 pokki/icon-large.png
convert original/icon.png -resize 256x256 pokki/icon-store.png

rm -rf tizen
mkdir tizen

convert original/icon.png -resize 117x117 tizen/icon.png

rm -rf ouya
mkdir ouya

convert original/splash-landscape.png -resize 732x412 ouya/splash.png

echo "  Build complete!"
