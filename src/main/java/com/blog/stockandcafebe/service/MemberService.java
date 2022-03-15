package com.blog.stockandcafebe.service;

import com.blog.stockandcafebe.dto.AccountDto;
import com.blog.stockandcafebe.entity.Account;

public interface MemberService {
    default Account dtoToEntity(AccountDto dto) {
        // TODO: implement method
        return null;
    }

    default AccountDto entityToDto(Account entity) {
        // TODO: implement method
        return null;
    }
}
