#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
# 현재 stop.sh가 속해 있는 경로 찾기
ABSDIR=$(dirname $ABSPATH)
# 자바의 import랑 같음 -> stop.sh에서도 profile.sh의 function들 사용 가능
source ${ABSDIR}/profile.sh

IDLE_PORT=$(find_idle_port)

echo "> $IDLE_PORT 에서 구동 중인 애플리케이션 pid 확인"
IDLE_PID=$(lsof -ti tcp:${IDLE_PORT})

if [ -z ${IDLE_PID} ]
then
  echo "> kill -15 $IDLE_PID"
  kill -15 ${IDLE_PID}
  sleep 5
fi