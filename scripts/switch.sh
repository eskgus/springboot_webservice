#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirnam $ABSPATH)
source ${ABSDIR}/profile.sh

function switch_proxy() {
    IDLE_PORT=$(find_idle_port)

    echo "> 전환할 port: $IDLE_PORT"
    echo "> port 전환"
    # "set ~"을 문장으로 만들어 파이프라인(|)으로 넘겨주려고 echo 사용
    # | 앞에서 넘겨준 문장을 service-url.inc에 덮어쓰기
    echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" | sudo tee etc/nginx/conf.d/service-url.inc

    echo "> nginx Reload"
    # restart랑 reload랑 다른 거
    # restart는 중요한 설정들 반영할 때 사용
    # reload는 아닐 때 사용 (외부의 설정 파일 service-url을 다시 불러오는 거라 여기서는 사용 가능)
    sudo service nginx reload
}