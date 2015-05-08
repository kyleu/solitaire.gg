#!/usr/bin/env bash
scp -i ~/.ssh/aws-ec2-key.pem ../target/universal/solitaire-gg-0.1-SNAPSHOT.zip ubuntu@52.6.71.25:~/solitaire.gg.zip
