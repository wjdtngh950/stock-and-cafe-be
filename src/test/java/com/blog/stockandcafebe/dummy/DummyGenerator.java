package com.blog.stockandcafebe.dummy;

import com.blog.stockandcafebe.blog.article.repository.ArticleRepository;
import com.blog.stockandcafebe.blog.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class DummyGenerator {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

//    @Test
//    void insertDummies() {
//
//        IntStream.rangeClosed(1, 300)
//                 .forEach(i -> {
//
//                     Member member = Member.builder()
//                                           .memberId((long) i)
//                                           .email("saddummy_" + i + "@gmail.com")
//                                           .password(passwordEncoder.encode("password"))
//                                           .name("Dummy_" + i)
//                                           .fromSocial(false)
//                                           .build();
//
//                     memberRepository.save(member);
//
//                     Article article = Article.builder()
//                                              .title("Title..." + i)
//                                              .content("Content..." + i)
//                                              .writer(member)
//                                              .build();
//
//                     System.out.println(articleRepository.save(article));
//
//                 });
//
//    }

}
