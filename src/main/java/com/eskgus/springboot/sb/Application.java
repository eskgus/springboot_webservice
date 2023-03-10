package com.eskgus.springboot.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication  // 스프링 부트의 자동 설정, 스프링 bean 읽기/생성 모두 자동으로 설정
public class Application {  // 프로젝트의 메인 클래스
    public static void main(String[] args) {

        // 새 파일이 commit 되면 이전 버전 중단시키고 업데이트 된 jar 실행하기 위해 application.properties에서 설정한
        // pid commend 값을 pid commend로 사용하려고 수정
        SpringApplication application = new SpringApplication(Application.class);
        application.addListeners(new ApplicationPidFileWriter());
        application.run(args);

    }
}
