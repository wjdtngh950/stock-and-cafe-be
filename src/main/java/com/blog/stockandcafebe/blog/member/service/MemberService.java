package com.blog.stockandcafebe.blog.member.service;

import com.blog.stockandcafebe.blog.member.controller.dto.MemberDto;
import com.blog.stockandcafebe.blog.member.repository.entity.Member;

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
                .createdDate(entity.getCreatedDate())
                .updatedDate(entity.getUpdatedDate())
                .build();
    }

    MemberDto register(MemberDto dto);
}
