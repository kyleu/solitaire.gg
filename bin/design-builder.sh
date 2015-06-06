#!/usr/bin/env bash
echo "Building all Solitaire.gg design resources..."

if [ ! -d ../offline/src/design ]; then
  echo "Missing ../offline/src/design"
  exit
fi

rm -rf "../offline/assets/design/*"
cd "../offline/assets/design"

cp -R "../../src/design" original

cd "../../../bin"
./design-desktop-builder.sh
./design-mobile-builder.sh

echo "Build complete!"
