package com.blog.stockandcafebe.blog.reply.service;

import com.blog.stockandcafebe.blog.article.repository.ArticleRepository;
import com.blog.stockandcafebe.blog.article.repository.entity.Article;
import com.blog.stockandcafebe.blog.common.dto.PageRequestDto;
import com.blog.stockandcafebe.blog.common.dto.PageResultDto;
import com.blog.stockandcafebe.blog.member.repository.MemberRepository;
import com.blog.stockandcafebe.blog.member.repository.entity.Member;
import com.blog.stockandcafebe.blog.reply.controller.dto.ReplyDto;
import com.blog.stockandcafebe.blog.reply.repository.ReplyRepository;
import com.blog.stockandcafebe.blog.reply.repository.entity.QReply;
import com.blog.stockandcafebe.blog.reply.repository.entity.Reply;
import com.blog.stockandcafebe.exception.article.ArticleNotExist;
import com.blog.stockandcafebe.exception.article.UnauthorizedArticleDelete;
import com.blog.stockandcafebe.exception.member.MemberNotExist;
import com.blog.stockandcafebe.exception.reply.ReplyNotExist;
import com.blog.stockandcafebe.exception.reply.UnauthorizedReplyModify;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyServiceImpl implements ReplyService {

    private final MemberRepository memberRepository;

    private final ArticleRepository articleRepository;

    private final ReplyRepository replyRepository;

    @Override
    public ReplyDto register(Long articleId, String writerEmail, ReplyDto dto) {

        Member member = memberRepository.findByEmail(writerEmail)
                .orElseThrow(MemberNotExist::new);

        Article article = articleRepository.findByArticleId(articleId)
                .orElseThrow(ArticleNotExist::new);

        Reply entity = Reply.builder()
                .replyId(dto.getReplyId())
                .text(dto.getText())
                .writer(member)
                .article(article)
                .build();

        Reply result = replyRepository.save(entity);

        return ReplyService.entityToDto(result);

    }

    @Override
    public PageResultDto<ReplyDto, Reply> getPageByArticleId(Long articleId, PageRequestDto requestDto) {

        Pageable pageable = requestDto.getPagable(Sort.by("replyId")
                .descending());

        BooleanBuilder booleanBuilder = getSearch(Optional.of(articleId), Optional.empty(), requestDto);

        Page<Reply> result = replyRepository.findAll(booleanBuilder, pageable);

        Function<Reply, ReplyDto> fn = ReplyService::entityToDto;

        return new PageResultDto<>(result, fn);

    }

    @Override
    public PageResultDto<ReplyDto, Reply> getPageByMemberId(Long memberId, PageRequestDto requestDto) {

        Pageable pageable = requestDto.getPagable(Sort.by("replyId")
                .descending());

        BooleanBuilder booleanBuilder = getSearch(Optional.empty(), Optional.of(memberId), requestDto);

        Page<Reply> result = replyRepository.findAll(booleanBuilder, pageable);

        Function<Reply, ReplyDto> fn = ReplyService::entityToDto;

        return new PageResultDto<>(result, fn);

    }

    @Override
    public ReplyDto modify(Long articleId, String writerEmail, Long replyId, ReplyDto replyDto) {

        Member member = memberRepository.findByEmail(writerEmail)
                .orElseThrow(MemberNotExist::new);

        Article article = articleRepository.findByArticleId(articleId)
                .orElseThrow(ArticleNotExist::new);

        Reply result = replyRepository.findByReplyId(replyId)
                .orElseThrow(ReplyNotExist::new);

        if (!result.getWriter().getEmail().equals(writerEmail)) {
            throw new UnauthorizedReplyModify();
        }

        if (Objects.nonNull(replyDto.getText())) {
            result.changeText(replyDto.getText());
        }

        return ReplyService.entityToDto(result);

    }

    @Override
    public void remove(Long articleId, String writerEmail, Long replyId) {

        Member member = memberRepository.findByEmail(writerEmail)
                .orElseThrow(MemberNotExist::new);

        Article article = articleRepository.findByArticleId(articleId)
                .orElseThrow(ArticleNotExist::new);

        Reply result = replyRepository.findByReplyId(replyId)
                .orElseThrow(ReplyNotExist::new);

        if (!result.getWriter().getEmail().equals(writerEmail)) {
            throw new UnauthorizedArticleDelete();
        }

        replyRepository.delete(result);

    }

    private BooleanBuilder getSearch(Optional<Long> articleId, Optional<Long> memberId, PageRequestDto requestDto) {

        String keyword = requestDto.getKeyword();

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QReply reply = QReply.reply;

        articleId.ifPresent(id -> booleanBuilder.and(reply.article.articleId.eq(id)));

        memberId.ifPresent(id -> booleanBuilder.and(reply.writer.memberId.eq(id)));

        booleanBuilder.and(reply.replyId.gt(0L));

        if (keyword == null || keyword.trim()
                .length() == 0) {
            return booleanBuilder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();

        conditionBuilder.or(reply.text.contains(keyword));

        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;

    }
}
