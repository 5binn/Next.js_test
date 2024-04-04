package com.restapi.test.article.controller;

import com.restapi.test.article.entity.Article;
import com.restapi.test.article.service.ArticleService;
import com.restapi.test.global.RsData.Rsdata;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @AllArgsConstructor
    @Getter
    public static class ListResponse {
        private List<Article> articleList;
    }

    @GetMapping("")
    public Rsdata<List<Article>> articleList() {
        return this.articleService.getArticleList();
    }

    @GetMapping("{id}")
    public Rsdata<Article> article(@PathVariable("id") Long id) {
        return this.articleService.getArticleById(id);
    }

    @AllArgsConstructor
    @Getter
    public static class ArticleForm {
        @NotBlank
        private String title;
        @NotBlank
        private String content;
    }

    @PostMapping("")
    public Rsdata<Article> create(@Valid @RequestBody ArticleForm articleForm) {
        return this.articleService.create(articleForm.getTitle(), articleForm.getContent());
    }

    @PatchMapping("{id}")
    public Rsdata<Article> update(@PathVariable("id") Long id, @Valid @RequestBody ArticleForm articleForm) {
        return this.articleService.update(id, articleForm.getTitle(), articleForm.getContent());
    }

    @DeleteMapping("{id}")
    public Rsdata<Article> delete(@PathVariable("id") Long id) {
        return this.articleService.delete(id);
    }
}
