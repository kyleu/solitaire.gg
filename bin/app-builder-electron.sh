#!/usr/bin/env bash
echo "  Building Electron apps..."

[[ "$PWD" != */bin ]] && echo "Please run this from the bin directory."
[[ "$PWD" != */bin ]] && exit

cd ..

# rm -rf build/linux.arm
# rm -rf build/linux.x32
# rm -rf build/linux.x64
# rm -rf build/osx
# rm -rf build/mas
# rm -rf build/win.x32
# rm -rf build/win.x64

mkdir -p build/electron/web

cp -R "build/web/" "build/electron/web"
cp -R "resources/offline/electron/" "build/electron/web"
asar pack build/electron/web build/asar/solitaire.gg.asar

cp build/asar/solitaire.gg.asar build/linux.arm/resources/app.asar
cp build/asar/solitaire.gg.asar build/linux.x32/resources/app.asar
cp build/asar/solitaire.gg.asar build/linux.x64/resources/app.asar

cp build/asar/solitaire.gg.asar build/osx/Solitaire.gg.app/Contents/Resources/app.asar
cp build/asar/solitaire.gg.asar build/mas/Solitaire.gg.app/Contents/Resources/app.asar

cp build/asar/solitaire.gg.asar build/win.x32/resources/app.asar
cp build/asar/solitaire.gg.asar build/win.x64/resources/app.asar

