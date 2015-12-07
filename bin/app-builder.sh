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

cd ../bin

./app-builder-electron.sh
./app-builder-cordova.sh

echo "  Build complete!"
