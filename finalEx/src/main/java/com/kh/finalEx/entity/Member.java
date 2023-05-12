package com.kh.finalEx.entity;
import com.kh.finalEx.constant.Authority;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "member")
@Getter @Setter @ToString
@NoArgsConstructor
public class Member {
    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userId;
    private String name;
    private String password;
    @Column(unique = true) //오라클에서 에러 발생 함 (이유 확인 못함)
    private String email;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column(name = "join_time")
    private LocalDateTime joinTime;

    @Builder
    public Member(Long id, String userId, String name, String password, String email, Authority authority, LocalDateTime joinTime) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.authority = authority;
        this.joinTime = joinTime;
    }
}
