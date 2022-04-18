package com.blog.stockandcafebe.blog.reply.repository.entity;

import com.blog.stockandcafebe.blog.article.repository.entity.Article;
import com.blog.stockandcafebe.blog.common.entity.BaseEntity;
import com.blog.stockandcafebe.blog.member.repository.entity.Member;
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

    public void changeText(String text) {
        this.text = text;
    }

}
