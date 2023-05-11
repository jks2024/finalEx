package com.kh.finalEx.repository;

import com.kh.finalEx.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
    boolean existsAllByEmail(String email);
    Member findByUserId(String userId);
    List<Member> findByUserIdAndPassword(String userId, String pwd);
    List<Member> findAll();
}
