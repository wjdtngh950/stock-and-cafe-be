package com.blog.stockandcafebe.service;

import com.blog.stockandcafebe.dto.ReplyDto;
import com.blog.stockandcafebe.entity.Reply;

public interface ReplyService {
    default Reply dtoToEntity(ReplyDto dto) {
        // TODO: implement method
        return null;
    }

    default ReplyDto entityToDto(Reply entity) {
        // TODO: implement method
        return null;
    }
}
