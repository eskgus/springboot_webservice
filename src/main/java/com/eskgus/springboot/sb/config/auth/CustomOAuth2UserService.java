package com.eskgus.springboot.sb.config.auth;

import com.eskgus.springboot.sb.config.auth.dto.OAuthAttributes;
import com.eskgus.springboot.sb.config.auth.dto.SessionUser;
import com.eskgus.springboot.sb.domain.user.User;
import com.eskgus.springboot.sb.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
// 구글 로그인 이후 가져온 사용자 정보들 기반으로 가입, 정보 수정, 세션 저장 기능
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 현재 로그인 중인 서비스 구분 (구글, 네이버)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // OAuth 2 로그인 진행 시 키가 되는 필드 값 (Primary Key랑 같은 뜻)
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        // OAuthAttributes: OAuth2UserService를 통해 가져온 OAuth2User의 attribute 담은 클래스
        OAuthAttributes attributes = OAuthAttributes
                .of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);
        // SessionUser: 세션에 사용자 정보 저장하기 위한 Dto 클래스
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(), attributes.getNameAttributeKey());

    }

    // (구글 사용자 정보 업데이트 됐을 때) 사용자의 이름이나 프로필 사진이 변경되면 User Entity에도 반영
    private User saveOrUpdate(OAuthAttributes attributes) {

        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);

    }

}
