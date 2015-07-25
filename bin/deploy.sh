#!/usr/bin/env bash

rm -rf ../target/universal/solitaire-gg-0.1-SNAPSHOT/*
unzip ../target/universal/solitaire-gg-0.1-SNAPSHOT.zip -d ../target/universal/

rsync -zrv --delete -e "ssh -i /Users/kyle/.ssh/aws-ec2-key.pem" ../target/universal/solitaire-gg-0.1-SNAPSHOT/* ubuntu@solitaire.gg:~/deploy/solitaire.gg
