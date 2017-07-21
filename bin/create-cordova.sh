#!/usr/bin/env bash
echo "  Creating Cordova apps..."

[[ "$PWD" != */bin ]] && echo "Please run this from the bin directory."
[[ "$PWD" != */bin ]] && exit

cd ..

rm -rf build/cordova

cd build

cordova create cordova

cd cordova

cp ../../resources/offline/cordova/config.xml .

cordova platform add browser
cordova platform add ios
cordova platform add android

cd platforms/ios/cordova/node_modules
npm install ios-sim@latest

cordova run ios
