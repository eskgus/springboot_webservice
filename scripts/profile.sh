#!/usr/bin/env bash

# 쉬고 있는 profile 찾기: real1이 사용 중이면 real2이 쉬고 있고, 반대면 real1이 쉬는 중
function find_idle_profile() {
    # 현재 nginx가 바라보고 있는 스프링 부트가 제대로 일하고 있는지 확인
    RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)

    # 응답 값 HttpStatus가 정상이면 200, 오류면 400~503 => 400 이상은 모두 예외로 보고 real2를 현재 profile로 사용
    if [ ${RESPONSE_CODE} -ge 400] # 400보다 크면(40x, 50x 에러 모두 포함)
    then
      CURRENT_PROFILE=real2
    else
      CURRENT_PROFILE=$(curl -s http://localhost/profile)
    fi

    if [ ${CURRENT_PROFILE} == real1 ]
    then
      IDLE_PROFILE=real2
    else
      IDLE_PROFILE=real1
    fi

    # bash 스크립트는 값 반환하는 게 x => 제일 마지막 줄에 echo로 결과 출력하고,
    # 그 값 잡아서($(find_idle_profile)) 사용
    echo "${IDLE_PROFILE}"
}

# 쉬고 있는 profile의 port 찾기
function find_idle_port() {
    IDLE_PROFILE=$(find_idle_profile)

    if [ ${IDLE_PROFILE} == real1 ]
    then
      echo "8081"
    else
      echo "8082"
    fi
}