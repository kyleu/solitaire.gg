#!/usr/bin/env bash

rm -rf ../target/universal/solitaire-gg-0.1-SNAPSHOT/*
unzip ../target/universal/solitaire-gg-0.1-SNAPSHOT.zip -d ../target/universal/

rsync -zrv --delete ../target/universal/solitaire-gg-0.1-SNAPSHOT/* kyle@solitaire.gg:~/apps/solitaire.gg
