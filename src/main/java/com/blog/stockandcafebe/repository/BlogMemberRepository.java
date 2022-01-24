package com.blog.stockandcafebe.repository;

import com.blog.stockandcafebe.entity.BlogMember;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BlogMemberRepository extends JpaRepository<BlogMember, Long> {
    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from BlogMember m where m.fromSocial =:social and m.email =:email")
    Optional<BlogMember> findByEmail(String email, boolean social);
}
