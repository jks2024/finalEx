package com.kh.finalEx.dto;

import com.kh.finalEx.constant.Authority;
import com.kh.finalEx.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRequestDto {
    private String userId;
    private String password;
    private String name;
    private String email;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .userId(userId)
      //          .password(passwordEncoder.encode(password))
                .password(password)
                .name(name)
                .email(email)
                .authority(Authority.ROLE_USER)
                .build();
    }
    // 아이디와 비밀번호가 일치하는 확인하는 로직
    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(userId, password);
    }
}
