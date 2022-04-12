package com.blog.stockandcafebe.blog.service;

import com.blog.stockandcafebe.blog.dto.MemberDto;
import com.blog.stockandcafebe.blog.entity.Member;
import com.blog.stockandcafebe.blog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {
    
    private final MemberRepository memberRepository;
    
    @Override
    public MemberDto register(MemberDto dto) {
        Member member = memberRepository.save(MemberService.dtoToEntity(dto));
        return MemberService.entityToDto(member);
    }
}
