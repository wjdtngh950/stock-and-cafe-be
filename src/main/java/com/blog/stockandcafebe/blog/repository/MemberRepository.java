package com.blog.stockandcafebe.blog.repository;

import com.blog.stockandcafebe.blog.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
