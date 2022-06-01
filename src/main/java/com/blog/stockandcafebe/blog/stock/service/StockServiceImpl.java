package com.blog.stockandcafebe.blog.stock.service;

import com.blog.stockandcafebe.blog.article.repository.entity.Article;
import com.blog.stockandcafebe.blog.article.service.ArticleService;
import com.blog.stockandcafebe.blog.common.dto.PageRequestDto;
import com.blog.stockandcafebe.blog.common.dto.PageResultDto;
import com.blog.stockandcafebe.blog.member.repository.MemberRepository;
import com.blog.stockandcafebe.blog.member.repository.entity.Member;
import com.blog.stockandcafebe.blog.stock.controller.dto.StockDto;
import com.blog.stockandcafebe.blog.stock.controller.dto.StockResponseDto;
import com.blog.stockandcafebe.blog.stock.repository.StockRepository;
import com.blog.stockandcafebe.blog.stock.repository.entity.QStock;
import com.blog.stockandcafebe.blog.stock.repository.entity.Stock;
import com.blog.stockandcafebe.exception.article.ArticleNotExist;
import com.blog.stockandcafebe.exception.article.UnauthorizedArticleDelete;
import com.blog.stockandcafebe.exception.article.UnauthorizedArticleModify;
import com.blog.stockandcafebe.exception.member.MemberNotExist;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Transactional
public class StockServiceImpl implements StockService {

    private final MemberRepository memberRepository;

    private final StockRepository stockRepository;

    @Override
    public StockDto register(String writerEmail, StockDto dto) {
        Member member = memberRepository.findByEmail(writerEmail)
                .orElseThrow(MemberNotExist::new);

        Stock entity = Stock.builder()
                .stockId(dto.getStockId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

        Stock result = stockRepository.save(entity);

        return StockService.entityToDto(result);
    }

    @Override
    public StockResponseDto getDetail(Long stockId) {
        Stock result = stockRepository.findByStockId(stockId)
                .orElseThrow(ArticleNotExist::new);

        return StockService.entityToResponseDto(result);
    }

    @Override
    public PageResultDto<StockResponseDto, Stock> getPage(PageRequestDto requestDto) {
        Pageable pageable = requestDto.getPagable(Sort.by("stockId")
                .descending());

        BooleanBuilder booleanBuilder = getSearch(requestDto);

        Page<Stock> result = stockRepository.findAll(booleanBuilder, pageable);

        Function<Stock, StockResponseDto> fn = StockService::entityToResponseDto;

        return new PageResultDto<>(result, fn);
    }

    @Override
    public StockResponseDto modify(Long stockId, String writerEmail, StockDto dto) {

        Stock result = stockRepository.findByStockId(stockId)
                .orElseThrow(ArticleNotExist::new);

        if (!result.getWriter().getEmail().equals(writerEmail)) {
            throw new UnauthorizedArticleModify();
        }

        if (Objects.nonNull(dto.getTitle())) {
            result.changeTitle(dto.getTitle());
        }

        if (Objects.nonNull(dto.getContent())) {
            result.changeContent(dto.getContent());
        }

        return StockService.entityToResponseDto(result);
    }

    @Override
    public void remove(Long stockId, String writerEmail) {
        Stock result = stockRepository.findByStockId(stockId)
                .orElseThrow(ArticleNotExist::new);

        if (!result.getWriter().getEmail().equals(writerEmail)) {
            throw new UnauthorizedArticleDelete();
        }

        stockRepository.delete(result);
    }

    private BooleanBuilder getSearch(PageRequestDto requestDto) {
        String type = requestDto.getType();

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QStock stock = QStock.stock;

        String keyword = requestDto.getKeyword();

        BooleanExpression expression = stock.stockId.gt(0L);

        booleanBuilder.and(expression);

        if (type == null || type.trim()
                .length() == 0) {
            return booleanBuilder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if (type.contains("t")) {
            conditionBuilder.or(stock.title.contains(keyword));
        }
        if (type.contains("c")) {
            conditionBuilder.or(stock.content.contains(keyword));
        }
        if (type.contains("w")) {
            conditionBuilder.or(stock.writer.name.contains(keyword));
        }

        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }
}
