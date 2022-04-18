package com.blog.stockandcafebe.blog.reply.repository;

import com.blog.stockandcafebe.blog.reply.repository.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    Optional<Reply> findByReplyId(Long replyId);
}
