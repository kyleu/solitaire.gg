#!/usr/bin/env bash
echo "  Building Electron apps..."

cd ../offline

rm -rf bin/linux.arm
rm -rf bin/linux.x32
rm -rf bin/linux.x64
rm -rf bin/osx
rm -rf bin/win.x32
rm -rf bin/win.x64

cp -R "assets/electron/" "bin"

mkdir -p build/electron
cp -R "build/web/" "build/electron"
cp -R "src/electron/" "build/electron"
asar pack build/electron build/solitaire.gg.asar

cp build/solitaire.gg.asar bin/linux.arm/resources/app.asar
cp build/solitaire.gg.asar bin/linux.x32/resources/app.asar
cp build/solitaire.gg.asar bin/linux.x64/resources/app.asar

cp build/solitaire.gg.asar bin/win.x32/resources/app.asar
cp build/solitaire.gg.asar bin/win.x64/resources/app.asar

cp build/solitaire.gg.asar bin/osx/Solitaire.gg.app/Contents/Resources/app.asar
