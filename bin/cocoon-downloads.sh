#!/bin/bash

cd ../offline/resources

rm -rf ./work/*

mkdir ./work
mkdir ./work/out

# Amazon App Store
unzip ~/Downloads/cocoon.js/android-amazon -d ./work/android-amazon
mv ./work/android-amazon/*_AmazonAppstore_debug_signed.apk ./mobile/Solitaire.gg_amazon_debug.apk
mv ./work/android-amazon/*_AmazonAppstore_release_unsigned.apk ./work/out/Solitaire.gg_amazon_release_unsigned.apk
jarsigner -storepass omgWTFdragonz! -keypass omgWTFdragonz! -sigalg SHA1withRSA -digestalg SHA1 -keystore ../../conf/certs/android.keystore ./work/out/Solitaire.gg_amazon_release_unsigned.apk android
jarsigner -verify -certs ./work/out/Solitaire.gg_amazon_release_unsigned.apk
zipalign -v 4 ./work/out/Solitaire.gg_amazon_release_unsigned.apk ./mobile/Solitaire.gg_amazon.apk

# Ouya
unzip ~/Downloads/cocoon.js/android-ouya -d ./work/android-ouya
mv ./work/android-ouya/*_Ouya_debug_signed.apk ./mobile/Solitaire.gg_ouya_debug.apk
mv ./work/android-ouya/*_Ouya_release_unsigned.apk ./work/out/Solitaire.gg_ouya_release_unsigned.apk
jarsigner -storepass omgWTFdragonz! -keypass omgWTFdragonz! -sigalg SHA1withRSA -digestalg SHA1 -keystore ../../conf/certs/android.keystore ./work/out/Solitaire.gg_ouya_release_unsigned.apk android
jarsigner -verify -certs ./work/out/Solitaire.gg_ouya_release_unsigned.apk
zipalign -v 4 ./work/out/Solitaire.gg_ouya_release_unsigned.apk ./mobile/Solitaire.gg_ouya.apk

# Google Play
unzip ~/Downloads/cocoon.js/android-play -d ./work/android-play
mv ./work/android-play/*_GooglePlaystoreV3_debug_signed.apk ./mobile/Solitaire.gg_play_debug.apk
mv ./work/android-play/*_GooglePlaystoreV3_release_unsigned.apk ./work/out/Solitaire.gg_play_release_unsigned.apk
jarsigner -storepass omgWTFdragonz! -keypass omgWTFdragonz! -sigalg SHA1withRSA -digestalg SHA1 -keystore ../../conf/certs/android.keystore ./work/out/Solitaire.gg_play_release_unsigned.apk android
jarsigner -verify -certs ./work/out/Solitaire.gg_play_release_unsigned.apk
zipalign -v 4 ./work/out/Solitaire.gg_play_release_unsigned.apk ./mobile/Solitaire.gg_play.apk

# iOS
unzip ~/Downloads/cocoon.js/ios -d ./work/ios
rm -rf ./mobile/*.xcodeproj
rm ./mobile/README.TXT
unzip ~/Downloads/cocoon.js/ios -d ./mobile
rm ./mobile/README.TXT

# Motherfuckin' Pokki
unzip ~/Downloads/cocoon.js/pokki -d ./work/pokki
mv ./work/pokki/*_PokkiApp.zip ./work/out/Solitaire.gg_pokki.zip

# Android Wear? Why not!
# unzip ~/Downloads/cocoon.js/android-wear -d ./work/android-wear
# mv ./work/android-wear/*_GooglePlaystoreV3_debug_signed.apk ./mobile/Solitaire.gg_wear_debug.apk
# mv ./work/android-wear/*_GooglePlaystoreV3_release_unsigned.apk ./work/out/Solitaire.gg_wear_release_unsigned.apk
# jarsigner -storepass omgWTFdragonz! -keypass omgWTFdragonz! -sigalg SHA1withRSA -digestalg SHA1 -keystore ../../conf/certs/android.keystore ./work/out/Solitaire.gg_wear_release_unsigned.apk android
# jarsigner -verify -certs ./work/out/Solitaire.gg_wear_release_unsigned.apk
# zipalign -v 4 ./work/out/Solitaire.gg_wear_release_unsigned.apk ./mobile/Solitaire.gg_wear.apk

# $25 Chinese Linux phones? Supported!
# unzip tinzen -d ./work/tinzen

rm -rf ./work
