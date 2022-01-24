package com.blog.stockandcafebe.service;

import com.blog.stockandcafebe.dto.MemberDto;
import com.blog.stockandcafebe.entity.BlogMember;

public interface MemberService {
    default BlogMember dtoToEntity(MemberDto dto) {
        // TODO: implement method
        return null;
    }

    default MemberDto entityToDto(BlogMember entity) {
        // TODO: implement method
        return null;
    }
}
