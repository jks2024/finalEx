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
    private String email;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Member(String user, String email, String password, String name, Authority authority) {
        this.userId = user;
        this.email = email;
        this.password = password;
        this.name = name;
        this.authority = authority;
    }

}
 