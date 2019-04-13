#!/usr/bin/env bash
echo "build successfully, deploying to the server..."
cd /var/lib/jenkins/workspace/whattocook
echo "kill the existing process"
ps -ef|grep -e root|grep -e SNAPSHOT|awk '{print $2}'|xargs sudo kill -9
echo "process killed, removing target folder..."
rm -rf target
echo "generating new target..."
mvn clean package -Dmaven.test.skip=true
cd target
echo "run a new process"
nohup java -jar benjamin-0.0.1-SNAPSHOT.jar > runLog.file
echo "back end deployment completed!"