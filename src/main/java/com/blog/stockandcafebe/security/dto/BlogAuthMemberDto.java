package com.blog.stockandcafebe.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

// TODO: Fix me
@Slf4j
@Getter
@Setter
@ToString
public class BlogAuthMemberDto extends User {
    private String email;
    private String name;
    private boolean fromSocial;

    public BlogAuthMemberDto(
            String username,
            String password,
            boolean fromSocial,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.email = username;
        this.fromSocial = fromSocial;
    }
}
