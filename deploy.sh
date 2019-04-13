#!/usr/bin/env bash
#post build scripts
echo "build successfully, deploying to the server..."
cd /var/lib/jenkins/workspace/whattocook
echo "kill the existing process"
pid=`ps |grep java|awk '{print $1}'`
if [[ "$pid" = "" ]]
then
  echo "no process exists... move on to mvn compiling"
else
  ps |grep java|awk '{print $1}'|xargs sudo kill -9
  echo "There is a process running"
fi
BUILD_ID=dontKillMe nohup /home/deploy/startup.sh &