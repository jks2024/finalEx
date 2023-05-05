package com.kh.finalEx.service;

import com.kh.finalEx.dto.MemberDto;
import com.kh.finalEx.entity.MemberInfo;
import com.kh.finalEx.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Service
@Transactional
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean regMember(String userId, String pwd, String name, String mail) {
        MemberInfo member = new MemberInfo();
        member.setUserId(userId);
        member.setPassword(pwd);
        member.setName(name);
        member.setEmail(mail);
        member.setJoin(LocalDateTime.now());
        if(regMemberCheck(member)) {
            memberRepository.save(member);
            return true;
        }
        return false;
    }

    public boolean regMemberCheck(MemberInfo member) {
        MemberInfo findMember = memberRepository.findByEmail(member.getEmail());
        return findMember == null;
    }
    // 로그인 체크
    public boolean loginCheck(String userId, String pwd) {
        List<MemberInfo> memberInfoList = memberRepository.findByUserIdAndPassword(userId, pwd);
        for(MemberInfo info : memberInfoList) {
            return true;
        }
        return false;
    }
    // 회원 조회
    public List<MemberDto> getMember(String userId) {
        List<MemberInfo> memberInfoList;
        if(userId.equals("ALL")) {
            memberInfoList = memberRepository.findAll();
        } else {
            memberInfoList = memberRepository.findByUserId(userId);
        }

        List<MemberDto> memberDtos = new ArrayList<>();
        for(MemberInfo info : memberInfoList) {
            MemberDto memberDto = new MemberDto();
            memberDto.setUser(info.getUserId());
            memberDto.setPwd(info.getPassword());
        }
        return memberDtos;
    }
}
