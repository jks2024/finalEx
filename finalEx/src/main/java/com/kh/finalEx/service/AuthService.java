package com.kh.finalEx.service;

import com.kh.finalEx.dto.MemberRequestDto;
import com.kh.finalEx.dto.MemberResponseDto;
import com.kh.finalEx.dto.TokenDto;
import com.kh.finalEx.entity.Member;
import com.kh.finalEx.jwt.TokenProvider;
import com.kh.finalEx.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final AuthenticationManagerBuilder managerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public MemberResponseDto signup(MemberRequestDto requestDto) {
        if(memberRepository.existsAllByEmail(requestDto.getEmail())) {
            throw new RuntimeException("이미 가입된 유저 입니다.");
        }
        Member member = requestDto.toMember(passwordEncoder);
        return MemberResponseDto.of(memberRepository.save(member));
    }

    public TokenDto login(MemberRequestDto requestDto) {
        System.out.println("TokenDTO 함수 호출");
        UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();
        System.out.println("인증 토큰 : " + authenticationToken);
        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);
        System.out.println("인증서 : " + authentication);

        return tokenProvider.generateTokenDto(authentication);
    }
}
