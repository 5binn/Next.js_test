package com.restapi.test.article.service;

import com.restapi.test.article.entity.Article;
import com.restapi.test.article.repository.ArticleRepository;
import com.restapi.test.global.RsData.Rsdata;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Rsdata<List<Article>> getArticleList() {
        return Rsdata.of(
                "S-1",
                "성공",
                this.articleRepository.findAll()
        );
    }

    public Rsdata<Article> getArticleById(Long id) {
        return this.articleRepository.findById(id).map((article) ->
                Rsdata.of(
                        "S-2",
                        "성공",
                        article
                )).orElseGet(() ->
                Rsdata.of(
                        "F-2",
                        "%d번 데이터 없음".formatted(id),
                        null
                ));
    }


    public Rsdata<Article> create(String title, String content) {
        try {
            Article article = Article.builder()
                    .title(title)
                    .content(content)
                    .build();
            this.articleRepository.save(article);
            return Rsdata.of(
                    "S-3",
                    "등록 성공",
                    article
            );
        }catch (Exception e) {
            return Rsdata.of(
                    "F-3",
                    "등록 실패",
                    null
            );
        }

    }

    public Rsdata<Article> update(Long id, String title, String content) {
        Rsdata<Article> rsdata = this.getArticleById(id);
        if (!rsdata.isSuccess()) {
            return rsdata;
        }
        try {
            Article updateArticle = rsdata.getData().toBuilder()
                    .title(title)
                    .content(content)
                    .build();
            this.articleRepository.save(updateArticle);
            return Rsdata.of(
                    "S-4",
                    "수정 성공",
                    updateArticle
            );
        } catch (Exception e) {
            return Rsdata.of(
                    "F-4",
                    "수정 실패",
                    null
            );
        }
    }

    public Rsdata<Article> delete(Long id) {
        Rsdata<Article> rsdata = this.getArticleById(id);
        if (!rsdata.isSuccess()) {
            return rsdata;
        }
        try {
            this.articleRepository.delete(rsdata.getData());
            return Rsdata.of(
                    "S-5",
                    "삭제 성공",
                    rsdata.getData()
            );
        } catch (Exception e) {
            return Rsdata.of(
                    "F-5",
                    "삭제 실패",
                    null
            );
        }
    }
}
