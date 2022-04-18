package com.blog.stockandcafebe.blog.member.repository.entity;

import com.blog.stockandcafebe.blog.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "account")
public class Member extends BaseEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(name = "email", unique = true, nullable = false, length = 256)
    private String email;

    @Column(name = "password", length = 1024)
    private String password;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "social_yn")
    private boolean fromSocial;
}
