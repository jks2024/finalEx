package com.kh.finalEx.service;

import com.kh.finalEx.config.security.SecurityUtil;
import com.kh.finalEx.dto.MemberDto;
import com.kh.finalEx.dto.MemberResponseDto;
import com.kh.finalEx.entity.Member;
import com.kh.finalEx.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberResponseDto getMyInfoBySecurity() {
        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
    }

    @Transactional
    public MemberResponseDto changeMemberNickname(String email, String name) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
        member.setName(name);
        return MemberResponseDto.of(memberRepository.save(member));
    }

    @Transactional
    public MemberResponseDto changeMemberPassword(String email, String exPassword, String newPassword) {
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
        if (!passwordEncoder.matches(exPassword, member.getPassword())) {
            throw new RuntimeException("비밀번호가 맞지 않습니다");
        }
        member.setPassword(passwordEncoder.encode((newPassword)));
        return MemberResponseDto.of(memberRepository.save(member));
    }

    // 회원 가입
    public boolean regMember(String userId, String pwd, String name, String mail) {
        Member member = new Member();
        member.setUserId(userId);
        member.setPassword(pwd);
        member.setName(name);
        member.setEmail(mail);
        if(regMemberCheck(member)) {
            memberRepository.save(member);
            return true;
        }
        return false;
    }

    public boolean regMemberCheck(Member member) {
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());
        return findMember.isEmpty();
    }
    // 로그인 체크
    public boolean loginCheck(String userId, String pwd) {
        List<Member> memberInfoList = memberRepository.findByUserIdAndPassword(userId, pwd);
        for(Member info : memberInfoList) {
            return true;
        }
        return false;
    }
    // 회원 조회
    public List<MemberDto> getMemberList() {
        List<Member> memberInfoList;
        memberInfoList = memberRepository.findAll();

        List<MemberDto> memberDtos = new ArrayList<>();
        for(Member info : memberInfoList) {
            MemberDto memberDto = new MemberDto();
            memberDto.setUser(info.getUserId());
            memberDto.setPwd(info.getPassword());
            memberDto.setName(info.getName());
            memberDto.setEmail(info.getEmail());
            memberDtos.add(memberDto);
        }
        return memberDtos;
    }
    // 회원 탈퇴
    public boolean delMember(String userId) {
        Member memberInfo = memberRepository.findByUserId(userId);
        if(memberInfo != null) {
            memberRepository.delete(memberInfo);
            return true;
        }
        return false;
    }
}
