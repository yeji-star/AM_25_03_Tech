package org.example.sv;

import org.example.ArticleManager.container;
import org.example.Util.Util;
import org.example.dao.ArticleDao;
import org.example.dto.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleService {

    private ArticleDao articleDao;

    public ArticleService() {
        articleDao = container.articleDao; //굳이 이걸 쓰지 않고 리스트 안에 리턴값을 container.~~ 으로 해도 됨.
    }

    public List<Article> getForPrintArticles(String searchKey) {
        List<Article> foPrintArticles = new ArrayList<>();

        //서치 키워드가 1 이상일 경우
        if (searchKey.length() > 0) {
            System.out.println("검색어 : " + searchKey);
//            searchkeyList = new ArrayList<>(); // 새로운 리스트를 만들고 있음. 그리고
//            //아티클스와 연결을 함.

            for (Article article : articleDao.getArticles()) {
                if (article.getTitle().contains(searchKey)) {
                    foPrintArticles.add(article);
                }
            }
            if (foPrintArticles.size() == 0) {
                System.out.println("검색 결과 없음");
                return foPrintArticles;
            }
        }
        return foPrintArticles;
    }

    public List<Article> getArticles() {
        return articleDao.articles;
    }

    public void add(Article article) {
        articleDao.add(article);
    }

    public int getSize() {
        return articleDao.getSize();
    }

    public Article getArticleById(int id) {
        return articleDao.getArticleById(id);
    }

    public void remove(Article article) {
        articleDao.remove(article);
    }

    public void updateArticle(Article article, String newTitle, String newBody) {
        articleDao.updateArticle(article, newTitle, newBody, Util.getOut());
    }
}
