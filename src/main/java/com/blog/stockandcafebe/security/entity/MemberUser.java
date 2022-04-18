package com.blog.stockandcafebe.security.entity;

import com.blog.stockandcafebe.blog.member.controller.dto.MemberDto;
import com.blog.stockandcafebe.blog.member.repository.entity.Member;
import com.blog.stockandcafebe.blog.member.service.MemberService;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Set;

@ToString
public class MemberUser extends User {

    private MemberDto memberDto;

    public MemberUser(Member member, Set<GrantedAuthority> grantedAuthorities) {
        super(member.getEmail(), member.getPassword(), grantedAuthorities);
        this.memberDto = MemberService.entityToDto(member);
    }

    public MemberDto getMemberDto() {
        return memberDto;
    }

}
