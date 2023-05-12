package com.kh.finalEx.service;

import com.kh.finalEx.dto.MemberDto;
import com.kh.finalEx.entity.Member;
import com.kh.finalEx.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
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





    // 회원 가입
    public boolean regMember(String userId, String pwd, String name, String mail) {
        Member member = new Member();
        member.setUserId(userId);
        member.setPassword(pwd);
        member.setName(name);
        member.setEmail(mail);
        member.setJoinTime(LocalDateTime.now());
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
