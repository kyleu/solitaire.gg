#!/usr/bin/env bash

rm -rf ../target/universal/solitaire-gg-0.1-SNAPSHOT/*
unzip ../target/universal/solitaire-gg-0.1-SNAPSHOT.zip -d ../target/universal/

rsync -zrv --delete ../target/universal/solitaire-gg-0.1-SNAPSHOT/* ubuntu@3.215.117.38:~/apps/solitaire.gg
