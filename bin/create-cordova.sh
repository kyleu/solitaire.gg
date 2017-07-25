#!/usr/bin/env bash
echo "  Creating Cordova apps..."

[[ "$PWD" != */bin ]] && echo "Please run this from the bin directory."
[[ "$PWD" != */bin ]] && exit

cd ..

rm -rf build/cordova

cd build

cordova create solitaire.gg

mv solitaire.gg cordova

cd cordova

cp ../../resources/offline/cordova/config.xml .

rm -rf web/*
cp -R ../web web

rm -rf res/*
cp -R ../../tmp/media/* res

cordova platform add browser
cordova platform add ios
cordova platform add android

cd platforms/ios/cordova/node_modules
npm install ios-sim@latest

cd ../../../..

cordova run ios
