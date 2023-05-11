package com.kh.finalEx.dto;

import com.kh.finalEx.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberResponseDto {
    private String userId;
    private String password;
    private String name;
    private String email;

    public static MemberResponseDto of(Member member) {
        return MemberResponseDto.builder()
                .userId(member.getUserId())
                .password(member.getPassword())
                .name(member.getName())
                .email(member.getEmail())
                .build();
    }
}
