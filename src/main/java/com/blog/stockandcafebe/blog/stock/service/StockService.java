package com.blog.stockandcafebe.blog.stock.service;

import com.blog.stockandcafebe.blog.article.controller.dto.ArticleDto;
import com.blog.stockandcafebe.blog.article.controller.dto.ArticleResponseDto;
import com.blog.stockandcafebe.blog.article.repository.entity.Article;
import com.blog.stockandcafebe.blog.common.dto.PageRequestDto;
import com.blog.stockandcafebe.blog.common.dto.PageResultDto;
import com.blog.stockandcafebe.blog.member.service.MemberService;
import com.blog.stockandcafebe.blog.stock.controller.dto.StockDto;
import com.blog.stockandcafebe.blog.stock.controller.dto.StockResponseDto;
import com.blog.stockandcafebe.blog.stock.repository.entity.Stock;

public interface StockService {

    static Stock dtoToEntity(StockDto dto) {
        return Stock.builder()
                .stockId(dto.getStockId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(MemberService.dtoToEntity(dto.getWriter()))
                .build();
    }

    static StockDto entityToDto(Stock entity) {
        return StockDto.builder()
                .stockId(entity.getStockId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(MemberService.entityToDto(entity.getWriter()))
                .createdDate(entity.getCreatedDate())
                .updatedDate(entity.getUpdatedDate())
                .build();
    }

    static StockResponseDto entityToResponseDto(Stock entity) {
        return StockResponseDto.builder()
                .stockId(entity.getStockId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writerEmail(entity.getWriter().getEmail())
                .writerName(entity.getWriter().getName())
                .createdDate(entity.getCreatedDate())
                .updatedDate(entity.getUpdatedDate())
                .build();
    }

    StockDto register(String writerEmail, StockDto dto);

    StockResponseDto getDetail(Long stockId);

    PageResultDto<StockResponseDto, Stock> getPage(PageRequestDto requestDto);

    StockResponseDto modify(Long stockId, String writerEmail, StockDto dto);

    void remove(Long stockId, String writerEmail);

}
