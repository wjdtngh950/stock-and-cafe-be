package com.blog.stockandcafebe.blog.stock.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockResponseDto {

    private Long stockId;

    private String title;

    private String content;

    private String writerEmail;

    private String writerName;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

}
