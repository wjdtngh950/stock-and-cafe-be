package com.blog.stockandcafebe.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    
    private Long memberId;
    
    private String email;
    
    private String password;
    
    private String name;
    
    private boolean fromSocial;
    
    private LocalDateTime createdDate;
    
    private LocalDateTime updatedDate;
    
}
