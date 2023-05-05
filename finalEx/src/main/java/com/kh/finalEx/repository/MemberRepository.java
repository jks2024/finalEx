package com.kh.finalEx.repository;

import com.kh.finalEx.entity.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<MemberInfo, Long> {
    MemberInfo findByEmail(String email);
    List<MemberInfo> findByUserId(String userId);
    List<MemberInfo> findByUserIdAndPassword(String userId, String pwd);
    List<MemberInfo> findAll();
}
