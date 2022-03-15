package com.blog.stockandcafebe.security.service;

import com.blog.stockandcafebe.entity.Account;
import com.blog.stockandcafebe.repository.AccountRepository;
import com.blog.stockandcafebe.security.dto.AccountAuthDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("AccountDetailsService loadUserByUsername " + username);
        Optional<Account> result = accountRepository.findByEmail(username, false);
        if (result.isEmpty())
            throw new UsernameNotFoundException("Check Email or Social");

        Account account = result.get();

        log.info("Account: " + account);

        AccountAuthDto accountAuthDto = new AccountAuthDto(
                account.getEmail(),
                account.getPassword(),
                account.isFromSocial(),
                account.getRoleSet().stream()
                       .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                       .collect(Collectors.toSet())
        );

        accountAuthDto.setName(account.getName());
        accountAuthDto.setFromSocial(account.isFromSocial());

        return accountAuthDto;
    }
}
