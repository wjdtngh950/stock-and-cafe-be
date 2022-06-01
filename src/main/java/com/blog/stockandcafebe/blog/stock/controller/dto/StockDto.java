package com.blog.stockandcafebe.blog.stock.controller.dto;

import com.blog.stockandcafebe.blog.member.controller.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockDto {

    private Long stockId;

    private String title;

    private String content;

    private MemberDto writer;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
