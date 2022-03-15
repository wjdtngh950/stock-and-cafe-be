package com.blog.stockandcafebe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;

@Builder
@AllArgsConstructor
@Data
public class AccountDto {
    private String email;
    private String password;
    private String name;
    private boolean fromSocial;
}
