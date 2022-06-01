package com.blog.stockandcafebe.blog.stock.controller;

import com.blog.stockandcafebe.blog.common.dto.PageRequestDto;
import com.blog.stockandcafebe.blog.common.dto.PageResultDto;
import com.blog.stockandcafebe.blog.stock.controller.dto.StockDto;
import com.blog.stockandcafebe.blog.stock.controller.dto.StockResponseDto;
import com.blog.stockandcafebe.blog.stock.repository.entity.Stock;
import com.blog.stockandcafebe.blog.stock.service.StockService;
import com.blog.stockandcafebe.security.entity.MemberUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<StockDto> register(
            @AuthenticationPrincipal MemberUser memberUser,
            @RequestBody StockDto stockDto
    ) {
        return new ResponseEntity<>(
                stockService.register(memberUser.getMemberDto().getEmail(), stockDto),
                HttpStatus.OK
        );
    }

    @GetMapping("/{stockId}")
    public ResponseEntity<StockResponseDto> read(@PathVariable Long stockId) {
        return new ResponseEntity<>(
                stockService.getDetail(stockId),
                HttpStatus.OK
        );
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResultDto<StockResponseDto, Stock>> getList(@ModelAttribute PageRequestDto pageRequestDto) {
        return new ResponseEntity<>(
                stockService.getPage(pageRequestDto),
                HttpStatus.OK
        );
    }

    @PatchMapping(value = "/{stockId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<StockResponseDto> modify(
            @PathVariable Long stockId,
            @AuthenticationPrincipal MemberUser memberUser,
            @RequestBody StockDto stockDto
    ) {
        return new ResponseEntity<>(
                stockService.modify(stockId, memberUser.getMemberDto().getEmail(), stockDto),
                HttpStatus.OK
        );
    }


    @DeleteMapping(value = "/{stockId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> remove(
            @PathVariable Long stockId,
            @AuthenticationPrincipal MemberUser memberUser
    ) {
        stockService.remove(stockId, memberUser.getMemberDto().getEmail());
        return new ResponseEntity<>(
                null,
                HttpStatus.OK
        );
    }

}
