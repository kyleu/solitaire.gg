#!/usr/bin/env bash
echo "  Building desktop resources..."

cd "../offline/assets/design"

echo "  - OS X"
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

echo "  - Windows"
rm -rf windows
mkdir windows

convert original/icon.png -resize 16x16 windows/icon_16x16.png
convert original/icon.png -resize 32x32 windows/icon_32x32.png
convert original/icon.png -resize 48x48 windows/icon_48x48.png
convert original/icon.png -resize 256x256 windows/icon_256x256.png
