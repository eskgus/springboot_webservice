package com.eskgus.springboot.sb.config.auth;

import com.eskgus.springboot.sb.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component  // Indicates that an annotated class is a "component"
// HandlerMethodArgumentResolver 인터페이스를 구현한 클래스
// HandlerMethodArgumentResolver: Strategy interface for resolving method parameters
//      into argument values in the context of a given request
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    @Override
    // Handler ~ 인터페이스의 메서드 구현
    // supportParameter(): Whether the given method parameter is supported by this resolver
    public boolean supportsParameter(MethodParameter parameter) {
        // parameter(매개 변수)에 @LoginUser 어노테이션이 붙어있으면(!= null) true
        boolean isLoginUserAnnotaion = parameter.getParameterAnnotation(LoginUser.class) != null;
        // parameter 클래스 타입이 SessionUser 클래스면 (equals) true
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());

        return isLoginUserAnnotaion && isUserClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // parameter에 전달할 객체 생성 => 세션에서 "user"의 attribute 가져와서 리턴
        return httpSession.getAttribute("user");
    }

}
