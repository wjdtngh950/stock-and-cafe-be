package com.blog.stockandcafebe.blog.reply.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyResponseDto {

    private Long replyId;

    private String text;

    private String writerName;

    private Long articleId;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
