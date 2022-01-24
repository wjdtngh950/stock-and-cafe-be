package com.blog.stockandcafebe.security;

import com.blog.stockandcafebe.entity.Member;
import com.blog.stockandcafebe.entity.MemberRole;
import com.blog.stockandcafebe.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberTests {

    @Autowired
    private MemberRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .memberId("saddummy" + i)
                    .email("saddummy" + i + "@gmail.com")
                    .name("dummy" + i)
                    .fromSocial(false)
                    .password(passwordEncoder.encode("1234"))
                    .build();
            member.addMemberRole(MemberRole.USER);
            if (i > 80)
                member.addMemberRole(MemberRole.MANAGER);
            if (i > 90)
                member.addMemberRole(MemberRole.ADMIN);
            repository.save(member);
        });
    }

    @Test
    public void testRead() {
        Optional<Member> result = repository.findById("saddummy99", false);
        Member member = result.get();
        System.out.println(member);
    }
}
