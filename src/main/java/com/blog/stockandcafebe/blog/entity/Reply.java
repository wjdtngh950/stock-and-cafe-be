package com.blog.stockandcafebe.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Reply extends BaseEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;

}
