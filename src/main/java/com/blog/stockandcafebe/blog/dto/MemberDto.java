package com.blog.stockandcafebe.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class MemberDto {

    private Long memberId;

    private String email;

    private String password;

    private String name;

    private boolean fromSocial;

}
