package com.blog.stockandcafebe.security;

import com.blog.stockandcafebe.entity.Account;
import com.blog.stockandcafebe.entity.AccountRole;
import com.blog.stockandcafebe.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class AccountTests {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Account account = Account.builder()
                                     .email("saddummy" + i + "@gmail.com")
                                     .name("dummy" + i)
                                     .fromSocial(false)
                                     .password(passwordEncoder.encode("1234"))
                                     .build();
            account.addMemberRole(AccountRole.USER);
            if (i > 80)
                account.addMemberRole(AccountRole.MANAGER);
            if (i > 90)
                account.addMemberRole(AccountRole.ADMIN);
            repository.save(account);
        });
    }

    @Test
    public void testRead() {
        Optional<Account> result = repository.findByEmail("saddummy99@gmail.com", false);
        Account account = result.get();
        System.out.println(account);
    }
}
