package com.kh.finalEx.service;

import com.kh.finalEx.entity.MemberInfo;
import com.kh.finalEx.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

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
}
