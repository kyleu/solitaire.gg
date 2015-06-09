#!/usr/bin/env bash
echo "  Building mobile resources..."

cd "../offline/assets/design"

echo "  - iOS"
rm -rf ios
mkdir ios

convert original/icon.png -resize 57x57 ios/icon.png
convert original/icon.png -resize 114x114 ios/icon@2x.png
convert original/icon.png -resize 60x60 ios/icon-60.png
convert original/icon.png -resize 120x120 ios/icon-60@2x.png
convert original/icon.png -resize 180x180 ios/icon-60@3x.png
convert original/icon.png -resize 76x76 ios/icon-76.png
convert original/icon.png -resize 152x152 ios/icon-76@2x.png
convert original/icon.png -resize 40x40 ios/icon-40.png
convert original/icon.png -resize 80x80 ios/icon-40@2x.png
convert original/icon.png -resize 72x72 ios/icon-72.png
convert original/icon.png -resize 144x144 ios/icon-72@2x.png
convert original/icon.png -resize 29x29 ios/icon-small.png
convert original/icon.png -resize 58x58 ios/icon-small@2x.png
convert original/icon.png -resize 50x50 ios/icon-50.png
convert original/icon.png -resize 100x100 ios/icon-50@2x.png

convert original/splash-portrait.png -resize 320x480 ios/splash-iphone.png
convert original/splash-portrait.png -resize 640x960 ios/splash-iphone@2x.png
convert original/splash-portrait.png -resize 640x1136 ios/splash-iphone5.png
convert original/splash-portrait.png -resize 750x1334 ios/splash-iphone6.png
convert original/splash-portrait.png -resize 1242x2208 ios/splash-iphone6-plus-portrait.png
convert original/splash-landscape.png -resize 2208x1242 ios/splash-iphone6-plus-landscape.png
convert original/splash-portrait.png -resize 768x1024 ios/splash-ipad-portrait.png
convert original/splash-portrait.png -resize 1536x2048 ios/splash-ipad-portrait@2x.png
convert original/splash-landscape.png -resize 1024x768 ios/splash-ipad-landscape.png
convert original/splash-landscape.png -resize 2048x1536 ios/splash-ipad-landscape@2x.png

echo "  - Android"
rm -rf android
mkdir android

convert original/icon.png -resize 36x36 android/icon-ldpi.png
convert original/icon.png -resize 48x48 android/icon-mdpi.png
convert original/icon.png -resize 72x72 android/icon-hdpi.png
convert original/icon.png -resize 96x96 android/icon-xhdpi.png
convert original/icon.png -resize 144x144 android/icon-xxhdpi.png
convert original/icon.png -resize 192x192 android/icon-xxxhdpi.png

convert original/icon.png -resize 60x60 -gravity center -extent 120x60 android/store-logo.png

convert original/splash-landscape.png -resize 732x412 android/ouya-splash.png

convert original/splash-landscape.png -resize 960x640 android/splash-landscape-xhdpi.png
convert original/splash-landscape.png -resize 800x480 android/splash-landscape-hdpi.png
convert original/splash-landscape.png -resize 480x320 android/splash-landscape-mdpi.png
convert original/splash-landscape.png -resize 320x240 android/splash-landscape-ldpi.png

convert original/splash-portrait.png -resize 640x960 android/splash-portrait-xhdpi.png
convert original/splash-portrait.png -resize 480x800 android/splash-portrait-hdpi.png
convert original/splash-portrait.png -resize 320x480 android/splash-portrait-mdpi.png
convert original/splash-portrait.png -resize 240x320 android/splash-portrait-ldpi.png

echo "  - Firefox OS"
rm -rf firefoxos
mkdir firefoxos

convert original/icon.png -resize 60x60 firefoxos/icon.png
