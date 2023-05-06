package com.kh.finalEx.entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter @Setter @ToString
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
    @Column(name = "join_time")
    @CreationTimestamp
    private LocalDateTime joinTime;
}
