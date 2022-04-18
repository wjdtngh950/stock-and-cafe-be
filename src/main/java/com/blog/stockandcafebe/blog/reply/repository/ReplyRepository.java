package com.blog.stockandcafebe.blog.reply.repository;

import com.blog.stockandcafebe.blog.reply.repository.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Long>, QuerydslPredicateExecutor<Reply> {

    Optional<Reply> findByReplyId(Long replyId);
    
}
