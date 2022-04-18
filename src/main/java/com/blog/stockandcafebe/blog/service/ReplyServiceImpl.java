package com.blog.stockandcafebe.blog.service;

import com.blog.stockandcafebe.blog.dto.ReplyDto;
import com.blog.stockandcafebe.blog.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyServiceImpl implements ReplyService {
    
    private ReplyRepository replyRepository;
    
    @Override
    public ReplyDto register(ReplyDto dto) {
        return null;
    }
}
