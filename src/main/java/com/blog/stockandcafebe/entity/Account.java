package com.blog.stockandcafebe.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Account extends BaseEntity {

    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;


    @Column(name = "from_social", nullable = false)
    private boolean fromSocial;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<AccountRole> roleSet = new HashSet<>();

    public void addMemberRole(AccountRole accountRole) {
        roleSet.add(accountRole);
    }
}
