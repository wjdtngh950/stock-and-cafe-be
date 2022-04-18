package com.blog.stockandcafebe.blog.service;

import com.blog.stockandcafebe.blog.dto.ReplyDto;
import com.blog.stockandcafebe.blog.entity.Reply;

public interface ReplyService {
    static Reply dtoToEntity(ReplyDto dto) {
        return Reply.builder()
                    .replyId(dto.getReplyId())
                    .text(dto.getText())
                    .writer(MemberService.dtoToEntity(dto.getWriter()))
                    .article(ArticleService.dtoToEntity(dto.getArticle()))
                    .build();
    }
    
    static ReplyDto entityToDto(Reply entity) {
        return ReplyDto.builder()
                       .replyId(entity.getReplyId())
                       .text(entity.getText())
                       .writer(MemberService.entityToDto(entity.getWriter()))
                       .article(ArticleService.entityToDto(entity.getArticle()))
                       .createdDate(entity.getCreatedDate())
                       .updatedDate(entity.getUpdatedDate())
                       .build();
    }
    
    ReplyDto register(ReplyDto dto);
}
