package com.blog.stockandcafebe.blog.member.service;

import com.blog.stockandcafebe.blog.member.controller.dto.MemberDto;
import com.blog.stockandcafebe.blog.member.repository.MemberRepository;
import com.blog.stockandcafebe.blog.member.repository.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberDto register(MemberDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        Member member = memberRepository.save(MemberService.dtoToEntity(dto));
        return MemberService.entityToDto(member);
    }
}
