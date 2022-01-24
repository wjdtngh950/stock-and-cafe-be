package com.blog.stockandcafebe.security.service;

import com.blog.stockandcafebe.entity.BlogMember;
import com.blog.stockandcafebe.repository.BlogMemberRepository;
import com.blog.stockandcafebe.security.dto.BlogAuthMemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogUserDetailsService implements UserDetailsService {

    private final BlogMemberRepository blogMemberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("BlogUserDetailsService loadUserByUsername " + username);
        Optional<BlogMember> result = blogMemberRepository.findByEmail(username, false);
        if (result.isEmpty())
            throw new UsernameNotFoundException("Check Email or Social");

        BlogMember blogMember = result.get();

        log.info("Blog Member: " + blogMember);

        BlogAuthMemberDto blogAuthMember = new BlogAuthMemberDto(
                blogMember.getEmail(),
                blogMember.getPassword(),
                blogMember.isFromSocial(),
                blogMember.getRoleSet().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                        .collect(Collectors.toSet())
        );

        blogAuthMember.setName(blogMember.getName());
        blogAuthMember.setFromSocial(blogMember.isFromSocial());

        return blogAuthMember;
    }
}
