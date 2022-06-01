package com.blog.stockandcafebe.blog.stock.repository;

import com.blog.stockandcafebe.blog.stock.repository.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long>, QuerydslPredicateExecutor<Stock> {

    Optional<Stock> findByStockId(Long stockId);
}
