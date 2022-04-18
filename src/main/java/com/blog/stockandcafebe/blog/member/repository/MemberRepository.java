package com.blog.stockandcafebe.blog.member.repository;

import com.blog.stockandcafebe.blog.member.repository.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
