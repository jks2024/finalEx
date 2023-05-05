package com.kh.finalEx.repository;

import com.kh.finalEx.entity.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberInfo, Long> {
    MemberInfo findByEmail(String email);
}
