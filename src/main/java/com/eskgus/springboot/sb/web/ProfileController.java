package com.eskgus.springboot.sb.web;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProfileController {    // 배포 시 8081 쓸지, 8082 쓸지 판단하는 기준이 되는 api

    private final Environment env;

    @GetMapping("/profile")
    public String profile() {
        // real, oauth, real-db 등이 활성화돼있으면 3개가 모두 ActiveProfiles에 담겨있음
        List<String> profiles = Arrays.asList(env.getActiveProfiles());
        // real: step2, real1/real2: 무중단 배포에 사용될 profile
        List<String> realProfiles = Arrays.asList("real", "real1", "real2");
        String defaultProfile = profiles.isEmpty() ? "default" : profiles.get(0);

        return profiles.stream().filter(realProfiles::contains).findAny().orElse(defaultProfile);
    }

}
