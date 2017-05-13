#!/usr/bin/env bash

rm -rf ../target/universal/solitaire-gg-0.1-SNAPSHOT/*
unzip ../target/universal/solitaire-gg-0.1-SNAPSHOT.zip -d ../target/universal/

rsync -zrv --delete -e "ssh -i ~/.ssh/solitaire.pem" ../target/universal/solitaire-gg-0.1-SNAPSHOT/* ubuntu@beta.solitaire.gg:~/solitaire.gg/deploy
