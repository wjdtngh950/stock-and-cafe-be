package com.blog.stockandcafebe.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDto {
    
    private Long replyId;
    
    private String text;
    
    private MemberDto writer;
    
    private ArticleDto article;
    
    private LocalDateTime createdDate;
    
    private LocalDateTime updatedDate;
    
}
