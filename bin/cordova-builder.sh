cd ../offline/assets
cordova create cordova gg.solitaire Solitaire.gg
cd cordova

rm config.xml
cp ../../src/cordova/config.xml .

rm -rf www
cp -R ../../src/cordova/www ./www

# Platforms
cordova platform add ios
cordova platform add amazon-fireos
cordova platform add android
cordova platform add firefoxos
cordova platform add browser

# 1st Party Plugins
cordova plugin add cordova-plugin-device
cordova plugin add cordova-plugin-network-information
cordova plugin add cordova-plugin-splashscreen
cordova plugin add cordova-plugin-whitelist
# cordova plugin add cordova-plugin-console

# 3rd Party Plugins
cordova plugin add com.hughisaacs2.cordova.plugins.androidtvplugin
cordova plugin add cordova-plugin-game-center
