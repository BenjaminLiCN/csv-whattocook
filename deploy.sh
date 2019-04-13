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
cd target
echo "run a new process:nohup java -jar benjamin-0.0.1-SNAPSHOT.jar > runLog.file 2>&1 &"
nohup java -jar benjamin-0.0.1-SNAPSHOT.jar > runLog.file 2>&1 &
ps
echo "back end deployment completed!"
ps