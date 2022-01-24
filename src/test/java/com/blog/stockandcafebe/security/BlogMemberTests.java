package com.blog.stockandcafebe.security;

import com.blog.stockandcafebe.entity.BlogMember;
import com.blog.stockandcafebe.entity.BlogMemberRole;
import com.blog.stockandcafebe.repository.BlogMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BlogMemberTests {

    @Autowired
    private BlogMemberRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            BlogMember blogMember = BlogMember.builder()
                    .email("saddummy" + i + "@gmail.com")
                    .name("dummy" + i)
                    .fromSocial(false)
                    .password(passwordEncoder.encode("1234"))
                    .build();
            blogMember.addMemberRole(BlogMemberRole.USER);
            if (i > 80)
                blogMember.addMemberRole(BlogMemberRole.MANAGER);
            if (i > 90)
                blogMember.addMemberRole(BlogMemberRole.ADMIN);
            repository.save(blogMember);
        });
    }

    @Test
    public void testRead() {
        Optional<BlogMember> result = repository.findByEmail("saddummy99@gmail.com", false);
        BlogMember blogMember = result.get();
        System.out.println(blogMember);
    }
}
