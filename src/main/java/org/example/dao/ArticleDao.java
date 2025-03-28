package org.example.dao;

import org.example.dto.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleDao {
    public List<Article> articles;

    public ArticleDao() {
        articles = new ArrayList<>();
    }

    public void add(Article article) {
        articles.add(article);
    }

    public List<Article> getArticles() {
        return articles;
    }

    public int getSize() {
        return articles.size();
    }

    public void updateArticle(Article article, String newTitle, String newBody, String out) {
        article.setTitle(newTitle);
        article.setBody(newBody);
        article.setReDate(out);
    }

    public Article getArticleById(int id) {
        for (Article article : articles) {
            if (article.getId() == id) {
                return article;
            }
        }
        return null;
    }

    public void remove(Article article) {
        articles.remove(article);
    }
}
