package com.eskgus.springboot.sb.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)  // @LoginUser를 매개 변수로 할 거
// Annotations are to be recorded in the class file by the compiler and retained by the VM at run time,
// so they may be read reflectively
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
    // 다른 컨트롤러, 메서드에서 세션 값 필요할 때마다 SessionUser user = ~ 하는 거 번거로우니까 어노테이션 생성해서 쓸 거
}
