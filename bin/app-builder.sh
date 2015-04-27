#!/usr/bin/env bash
echo "Building Scalataire..."

if [ ! -d ../offline ]; then
  echo "Missing ../offline"
  exit
fi
cd "../offline"

_now=$(date +"%Y-%d-%m")
mkdir -p "build/dev"
mkdir -p "build/prod"
printf "Scalataire\\n  Build Generated: $_now" > "build/build-info"

rm -rf bin/dev
rm -rf bin/prod

echo "  Updating web assets..."

# Dev
mkdir -p build/dev/web
cp "../target/web/rjs/build/client-opt.js" "build/dev/web"
cp "../target/web/rjs/build/javascripts/main.js" "build/dev/web/scalataire.js"
cp "../target/web/less/main/stylesheets/scalataire.css" "build/dev/web"
cp "../target/web/less/main/stylesheets/scalataire.css.map" "build/dev/web"
cp "../app/assets/stylesheets/scalataire.less" "build/dev/web"

mkdir -p build/dev/web/lib
cp "../public/lib/phaser/js/phaser.js" "build/dev/web/lib"
cp "../public/lib/underscore/underscore.js" "build/dev/web/lib"
cp "../target/web/rjs/appdir/lib/requirejs/require.js" "build/dev/web/lib"

mkdir -p build/dev/web/assets
cp -R "../public/images/" "build/dev/web/assets/images"
rm -Rf "build/dev/web/assets/images/game/cards/standard/_resized"
rm -Rf "build/dev/web/assets/images/game/cards/standard/_unprocessed"
rm -Rf "build/dev/web/assets/images/game/cards/standard/x-large"

# Prod
mkdir -p build/prod/web
cp "../target/web/rjs/build/client-opt.js" "build/prod/web"
cp "../target/web/rjs/build/javascripts/main.js" "build/prod/web/scalataire.js"
cp "../target/web/less/main/stylesheets/scalataire.css" "build/prod/web"

mkdir -p build/prod/web/lib
cp "../public/lib/phaser/js/phaser.min.js" "build/prod/web/lib"
cp "../public/lib/underscore/underscore.min.js" "build/prod/web/lib"
cp "../target/web/rjs/build/lib/requirejs/require.min.js" "build/prod/web/lib"

mkdir -p build/prod/web/assets
cp -R "../public/images/" "build/prod/web/assets/images"
rm -Rf "build/prod/web/assets/images/game/cards/standard/_resized"
rm -Rf "build/prod/web/assets/images/game/cards/standard/_unprocessed"
rm -Rf "build/prod/web/assets/images/game/cards/standard/x-large"

echo "  Building Electron apps..."

# Dev
mkdir -p bin/dev
cp -R "resources/" "bin/dev"

mkdir -p build/dev/electron
cp -R "build/dev/web/" "build/dev/electron"
cp -R "electron/dev/" "build/dev/electron"
asar pack build/dev/electron bin/dev/scalataire.asar

cp bin/dev/scalataire.asar bin/dev/osx/Scalataire.app/Contents/Resources/app.asar
cp bin/dev/scalataire.asar bin/dev/linux/resources/app.asar
cp bin/dev/scalataire.asar bin/dev/win32/resources/app.asar
cp bin/dev/scalataire.asar bin/dev/win64/resources/app.asar

# Prod
mkdir -p bin/prod
cp -R "resources/" "bin/prod"

mkdir -p build/prod/electron
cp -R "build/prod/web/" "build/prod/electron"
cp -R "electron/prod/" "build/prod/electron"
asar pack build/prod/electron bin/prod/scalataire.asar

cp bin/dev/scalataire.asar bin/prod/osx/Scalataire.app/Contents/Resources/app.asar
cp bin/dev/scalataire.asar bin/prod/linux/resources/app.asar
cp bin/dev/scalataire.asar bin/prod/win32/resources/app.asar
cp bin/dev/scalataire.asar bin/prod/win64/resources/app.asar

echo "  Building Cocoon.js distribution..."

# Dev
mkdir -p build/dev/cocoon
cp -R "build/dev/web/" "build/dev/cocoon"
cp -R "cocoon/dev/" "build/dev/cocoon"
zip -r -q bin/dev/scalataire.zip build/dev/web/*

# Prod
mkdir -p build/prod/cocoon
cp -R "build/prod/web/" "build/prod/cocoon"
cp -R "cocoon/prod/" "build/prod/cocoon"
zip -r -q bin/prod/scalataire.zip build/prod/web/*

echo "  Build complete!"
