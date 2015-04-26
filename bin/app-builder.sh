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

echo "  Building Electron app..."

mkdir -p build/dev/electron
cp -R "build/dev/web/" "build/dev/electron"
cp -R "electron/dev/" "build/dev/electron"

mkdir -p build/prod/electron
cp -R "build/prod/web/" "build/prod/electron"
cp -R "electron/prod/" "build/prod/electron"

echo "  Build complete!"
