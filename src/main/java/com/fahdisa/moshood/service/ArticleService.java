package com.fahdisa.moshood.service;

import com.fahdisa.moshood.entity.*;
import com.fahdisa.moshood.util.BaseResponse;
import com.fahdisa.moshood.util.Codes;
import com.fahdisa.moshood.util.Page;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@RequestScoped
public class ArticleService {

    @PersistenceContext
    private EntityManager entityManager;

    public BaseResponse createArticle(Article article) {
        if (Objects.isNull(article)) {
            return new BaseResponse.Builder<>()
                    .setCode(Codes.INVALID_INPUT)
                    .setDescription("Invalid article")
                    .build();
        } else {
            entityManager.persist(article);
            return new BaseResponse.Builder<>()
                    .setCode(Codes.SUCCESS)
                    .setDescription("Success")
                    .setData(article)
                    .build();
        }
    }

    public BaseResponse updateArticle(Article article) {
        if (Objects.isNull(article) || Objects.isNull(article.getId())) {
            return new BaseResponse.Builder<>()
                    .setCode(Codes.INVALID_INPUT)
                    .setDescription("Invalid Article")
                    .build();
        } else {
            entityManager.merge(article);
            return new BaseResponse.Builder<>()
                    .setCode(Codes.SUCCESS)
                    .setDescription("Success")
                    .setData(article)
                    .build();
        }
    }

    public BaseResponse createSection(Long articleId, Section section) {
        if (Objects.isNull(articleId)) {
            return new BaseResponse.Builder<>()
                    .setCode(Codes.INVALID_INPUT)
                    .setDescription("Invalid article id")
                    .build();
        } else {
            if (Objects.isNull(section)) {
                return new BaseResponse.Builder<>()
                        .setCode(Codes.INVALID_INPUT)
                        .setDescription("Invalid section")
                        .build();
            } else {
                Article article = entityManager.find(Article.class, articleId);
                if (Objects.isNull(article)) {
                    return new BaseResponse.Builder<>()
                            .setCode(Codes.NO_RECORD)
                            .setDescription("Article not found")
                            .build();
                } else {
                    article.getSections().add(section);
                    Article merge = entityManager.merge(article);
                    return new BaseResponse.Builder<>()
                            .setCode(Codes.SUCCESS)
                            .setDescription("Success")
                            .setData(merge)
                            .build();
                }
            }
        }
    }

    public BaseResponse removeSection(Long sectionId) {
        if (Objects.isNull(sectionId)) {
            return new BaseResponse.Builder<>()
                    .setCode(Codes.INVALID_INPUT)
                    .setDescription("Invalid section")
                    .build();
        } else {
            Section section = entityManager.find(Section.class, sectionId);
            if (Objects.isNull(section)) {
                return new BaseResponse.Builder<>()
                        .setCode(Codes.NO_RECORD)
                        .setDescription("Section not found")
                        .build();
            } else {
                entityManager.remove(section);
                return new BaseResponse.Builder<>()
                        .setCode(Codes.SUCCESS)
                        .setDescription("Success")
                        .build();
            }
        }
    }

    public BaseResponse removeArticle(Long articleId) {
        if (Objects.isNull(articleId)) {
            return new BaseResponse.Builder<>()
                    .setCode(Codes.INVALID_INPUT)
                    .setDescription("Invalid article")
                    .build();
        } else {
            Article article = entityManager.find(Article.class, articleId);
            if (Objects.isNull(article)) {
                return new BaseResponse.Builder<>()
                        .setCode(Codes.NO_RECORD)
                        .setDescription("Article not found")
                        .build();
            } else {
                entityManager.remove(article);
                return new BaseResponse.Builder<>()
                        .setCode(Codes.SUCCESS)
                        .setDescription("Success")
                        .build();
            }
        }
    }

    public BaseResponse getArticle(Long articleId) {
        if (Objects.isNull(articleId)) {
            return new BaseResponse.Builder<>()
                    .setCode(Codes.INVALID_INPUT)
                    .setDescription("Invalid article")
                    .build();
        } else {
            Article article = entityManager.find(Article.class, articleId);
            if (Objects.isNull(article)) {
                return new BaseResponse.Builder<>()
                        .setCode(Codes.NO_RECORD)
                        .setDescription("Article not found")
                        .build();
            } else {
                return new BaseResponse.Builder<>()
                        .setCode(Codes.SUCCESS)
                        .setDescription("Success")
                        .setData(article)
                        .build();
            }
        }
    }

    public BaseResponse getArticles(Long lastId, Integer page, Integer size) {
        if (Objects.isNull(lastId)) {
            lastId = Page.DEFAULT_LAST_ID;
        }

        if (Objects.isNull(page)) {
            page = Page.DEFAULT_PAGE;
        }

        if (Objects.isNull(size)) {
            size = Page.DEFAULT_SIZE;
        }

        List<Article> articles = entityManager
                .createQuery("SELECT a FROM Article a WHERE a.id < :id ORDER BY a.id DESC",
                        Article.class).setParameter("id", lastId)
                .setFirstResult(page * size).setMaxResults(size)
                .getResultList();

        if (articles.isEmpty()) {
            return new BaseResponse.Builder<>()
                    .setCode(Codes.NO_RECORD)
                    .setDescription("No article found")
                    .build();
        } else {
            return new BaseResponse.Builder<>()
                    .setCode(Codes.SUCCESS)
                    .setDescription("Success")
                    .setData(articles)
                    .build();
        }
    }

    public BaseResponse createComment(Long articleId, Comment comment){
        if(Objects.isNull(articleId) || Objects.isNull(comment)){
            return new BaseResponse.Builder<>()
                    .setCode(Codes.INVALID_INPUT)
                    .setDescription("Invalid review")
                    .build();
        }else{
            Article article = entityManager.find(Article.class, articleId);
            if(Objects.isNull(article)){
                return new BaseResponse.Builder<>()
                        .setCode(Codes.NO_RECORD)
                        .setDescription("Article not found")
                        .build();
            }else{
                comment.setArticle(articleId);
                entityManager.persist(comment);
                return new BaseResponse.Builder<>()
                        .setCode(Codes.SUCCESS)
                        .setDescription("Success")
                        .setData(comment)
                        .build();
            }
        }
    }

    public BaseResponse getComments(Long articleId, Long lastId, Integer page, Integer size){
        if(Objects.isNull(articleId)){
            return new BaseResponse.Builder<>()
                    .setCode(Codes.INVALID_INPUT)
                    .setDescription("Invalid Article")
                    .build();
        }else{
            if(Objects.isNull(lastId) || (lastId < 1)){
                lastId = Page.DEFAULT_LAST_ID;
            }

            if(Objects.isNull(page) || (page < 0)){
                page = Page.DEFAULT_PAGE;
            }

            if(Objects.isNull(size) || (size < 1)){
                size = Page.DEFAULT_SIZE;
            }

            List<Comment> comments = entityManager
                    .createQuery("SELECT c FROM Comment c WHERE c.article = :article AND c.id < :id ORDER BY c.id DESC",
                            Comment.class).setParameter("article", articleId)
                    .setParameter("id", lastId).setFirstResult(page * size)
                    .setMaxResults(size).getResultList();
            if(comments.isEmpty()){
                return new BaseResponse.Builder<>()
                        .setCode(Codes.NO_RECORD)
                        .setDescription("No Comment found")
                        .build();
            }else{
                return new BaseResponse.Builder<>()
                        .setCode(Codes.SUCCESS)
                        .setDescription("Success")
                        .setData(comments)
                        .build();
            }
        }
    }

}
