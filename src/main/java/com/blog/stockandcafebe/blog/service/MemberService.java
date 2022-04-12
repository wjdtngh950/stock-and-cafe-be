package com.blog.stockandcafebe.blog.service;

import com.blog.stockandcafebe.blog.dto.MemberDto;
import com.blog.stockandcafebe.blog.entity.Member;

public interface MemberService {
    
    static Member dtoToEntity(MemberDto dto) {
        return Member.builder()
                     .memberId(dto.getMemberId())
                     .email(dto.getEmail())
                     .password(dto.getPassword())
                     .name(dto.getName())
                     .fromSocial(dto.isFromSocial())
                     .build();
    }
    
    static MemberDto entityToDto(Member entity) {
        return MemberDto.builder()
                        .memberId(entity.getMemberId())
                        .email(entity.getEmail())
                        .password(entity.getPassword())
                        .name(entity.getName())
                        .fromSocial(entity.isFromSocial())
                        .build();
    }
    
    MemberDto register(MemberDto dto);
}
