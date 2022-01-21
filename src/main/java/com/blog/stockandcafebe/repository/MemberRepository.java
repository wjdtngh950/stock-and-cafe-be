package com.blog.stockandcafebe.repository;

import com.blog.stockandcafebe.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
