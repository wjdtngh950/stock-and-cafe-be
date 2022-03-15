package com.blog.stockandcafebe.repository;

import com.blog.stockandcafebe.entity.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from Account m where m.fromSocial =:social and m.email =:email")
    Optional<Account> findByEmail(String email, boolean social);
}
