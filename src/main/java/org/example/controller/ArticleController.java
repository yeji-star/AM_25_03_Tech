package org.example.controller;

import org.example.ArticleManager.container;
import org.example.dto.Article;
import org.example.Util.Util;
import org.example.dto.Member;
import org.example.sv.ArticleService;
import org.example.sv.MemberService;

import java.util.List;
import java.util.Scanner;

public class ArticleController extends Controller {
    private Scanner sc;
    private String cmd;
    int lastArticleId = 3;


    private ArticleService articleService;
    private MemberService memberService;

    public ArticleController(Scanner sc) {
        this.sc = sc;
        this.articleService = container.articleService;
        this.memberService = container.memberService;
        this.members = memberService.getMembers();
    }

    public void doAction(String cmd, String actionMethodName) {
        this.cmd = cmd;

        switch (actionMethodName) {
            case "write":
                doWrite();
                break;
            case "list":
                showList();
                break;
            case "detail":
                showDetail();
                break;
            case "delete":
                doDelete();
                break;
            case "modify":
                doModi();
                break;

            default:
                System.out.println("Unknown action method");
                break;
        }
    }


    private void doWrite() {
        System.out.println("==게시글 작성==");
        int id = lastArticleId + 1;
        int memberId = loginMember.getId();
        String regDate = Util.getOut();
        String updateDate = Util.getOut();
        System.out.print("제목 : ");
        String title = sc.nextLine().trim();
        System.out.print("내용 : ");
        String body = sc.nextLine().trim();

        Article article = new Article(id, regDate, updateDate, memberId, title, body);
        articleService.add(article);

        System.out.println(id + "번 글이 작성되었습니다");
        lastArticleId++;
    }

    private void showList() {
        System.out.println("==게시글 목록==");

        if (articleService.getSize() == 0) {
            System.out.println("아무것도 없어");
            return;
        }
        //중요

        String searchKey = cmd.substring("article list".length()).trim();
        //서치키워드를 뽑아냄

        List<Article> searchkeyList = articleService.getForPrintArticles(searchKey); // 검색어가 없으면 바로 리스트로 갈 수 있게끔


        String writerName = null;

        //서치 키워드가 0일 경우 (아티클 리스트만 쳤을 경우)
        System.out.println("   번호    /     날짜       /     작성자       /   제목     /    내용   ");
        for (int i = searchkeyList.size() - 1; i >= 0; i--) {
            Article article = searchkeyList.get(i);
            for (Member member : members) {
                if (article.getMemberId() == member.getId()) {
                    writerName = member.getName();
                    break;
                }
            }

            if (Util.getOut().split(" ")[0].equals(article.getReDate().split(" ")[0])) {
                System.out.printf("  %d   /    %s        /    %s        /    %s     /    %s   \n", article.getId(), article.getReDate().split(" ")[1], writerName, article.getTitle(), article.getBody());
            } else {
                System.out.printf("  %d   /    %s        /    %s        /    %s     /    %s   \n", article.getId(), article.getReDate().split(" ")[0], writerName, article.getTitle(), article.getBody());
            }

        }

    }

    private void showDetail() {
        System.out.println("==게시글 상세보기==");

        int id = Integer.parseInt(cmd.split(" ")[2]);

        Article foundArticle = articleService.getArticleById(id);

        if (foundArticle == null) {
            System.out.println("해당 게시글은 없습니다");
            return;
        }

        String writerName = null;

        for (Member member : members) {
            if (foundArticle.getMemberId() == member.getId()) {
                writerName = member.getName();
                break;
            }
        }

        System.out.println("번호 : " + foundArticle.getId());
        System.out.println("작성날짜 : " + foundArticle.getDate());
        System.out.println("수정날짜 : " + foundArticle.getReDate());
        System.out.println("작성자 : " + writerName);
        System.out.println("제목 : " + foundArticle.getTitle());
        System.out.println("내용 : " + foundArticle.getBody());
    }

    private void doDelete() {
        System.out.println("==게시글 삭제==");

        int id = Integer.parseInt(cmd.split(" ")[2]);

        Article foundArticle = articleService.getArticleById(id);

        if (foundArticle == null) {
            System.out.println("해당 게시글은 없습니다");
            return;
        }

        if (foundArticle.getMemberId() != loginMember.getId()) {
            System.out.println("권한 없음");
            return;
        }

        articleService.remove(foundArticle);
        System.out.println(id + "번 게시글이 삭제되었습니다");
    }

    private void doModi() {
        System.out.println("==게시글 수정==");

        int id = Integer.parseInt(cmd.split(" ")[2]);

        Article foundArticle = articleService.getArticleById(id);

        if (foundArticle == null) {
            System.out.println("해당 게시글은 없습니다");
            return;
        }

        if (foundArticle.getMemberId() != loginMember.getId()) {
            System.out.println("권한 없음");
            return;
        }

        System.out.println("기존 제목 : " + foundArticle.getTitle());
        System.out.println("기존 내용 : " + foundArticle.getBody());
        System.out.print("새 제목 : ");
        String newTitle = sc.nextLine().trim();
        System.out.print("새 내용 : ");
        String newBody = sc.nextLine().trim();

        articleService.updateArticle(foundArticle, newTitle, newBody);

        System.out.println(id + "번 게시글이 수정되었습니다");
    }


    /**
     * 테스트 데이터 생성 함수
     **/
    public void makeTestData() {
        System.out.println("==글 목록 테스트 데이터 생성==");
        articleService.add(new Article(1, "2024-12-12 12:12:12", "2024-12-12 12:12:12", 1, "제목123", "내용1"));
        articleService.add(new Article(2, Util.getOut(), Util.getOut(), 1, "제목245", "내용2"));
        articleService.add(new Article(3, Util.getOut(), Util.getOut(), 2, "제목8753", "내용3"));
    }


}

