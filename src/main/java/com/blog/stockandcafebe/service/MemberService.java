package com.blog.stockandcafebe.service;

import com.blog.stockandcafebe.dto.MemberDto;
import com.blog.stockandcafebe.entity.Member;

public interface MemberService {
    default Member dtoToEntity(MemberDto dto) {
        // TODO: implement method
        return null;
    }

    default MemberDto entityToDto(Member entity) {
        // TODO: implement method
        return null;
    }
}
