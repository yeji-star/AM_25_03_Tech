package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Article> articles = new ArrayList<>();
    static List<Member> members = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("==프로그램 시작==");

        int lastArticleId = 3;
        int lastMemberId = 3;

        makeTestData();
        logmakeTestData();

        while (true) {
            System.out.print("명령어) ");
            String cmd = sc.nextLine().trim();

            if (cmd.length() == 0) {
                System.out.println("명령어를 입력하세요");
                continue;
            }
            if (cmd.equals("exit")) {
                break;
            }

            if (cmd.equals("member join")) {
                System.out.println("==회원가입==");
                int id = lastMemberId + 1;
                String regDate = Util.getOut();
                String loginId = null;
                while (true) {
                    System.out.print("로그인 아이디 : ");
                    loginId = sc.nextLine().trim();
                    if (isJoinableLoginId(loginId) == false) {
                        System.out.println("이미 사용중인 아이디입니다.");
                        continue;
                    }
                    break;
                }
                String password = null;
                while (true) {
                    System.out.print("비밀번호 : ");
                    password = sc.nextLine().trim();
                    System.out.print("비밀번호 확인 : ");
                    String passwordConfirm = sc.nextLine().trim();
                    if (password.equals(passwordConfirm) == false) {
                        System.out.println("비밀번호를 다시 확인해주세요.");
                        continue;
                    }
                    break;
                }

                System.out.println("이름 : ");
                String name = sc.nextLine().trim();

                Member member = new Member(id, regDate, loginId, password, name);
                members.add(member);

                System.out.println(id + "번 회원이 가입되었습니다");
                lastMemberId++;

            }
            else if (cmd.equals("article write")) {
                System.out.println("==게시글 작성==");
                int id = lastArticleId + 1;
                String regDate = Util.getOut();
                String updateDate = Util.getOut();
                System.out.print("제목 : ");
                String title = sc.nextLine().trim();
                System.out.print("내용 : ");
                String body = sc.nextLine().trim();

                Article article = new Article(id, regDate, updateDate, title, body);
                articles.add(article);

                System.out.println(id + "번 글이 작성되었습니다");
                lastArticleId++;
            }
            else if (cmd.startsWith("article list")) {
                System.out.println("==게시글 목록==");
                if (articles.size() == 0) {
                    System.out.println("아무것도 없어");
                    continue;
                }
                //중요

                String searchKey = cmd.substring("article list".length()).trim();
                //서치키워드를 뽑아냄

                List<Article> searchkeyList = articles; // 검색어가 없으면 바로 리스트로 갈 수 있게끔

                //서치 키워드가 1 이상일 경우
                if (searchKey.length() > 0) {
                    System.out.println("검색어 : " + searchKey);
                    searchkeyList = new ArrayList<>(); // 새로운 리스트를 만들고 있음. 그리고
                    //아티클스와 연결을 함.

                    for (Article article : articles) {
                        if (article.getTitle().contains(searchKey)) {
                            searchkeyList.add(article);
                        }
                    }
                    if (searchkeyList.size() == 0) {
                        System.out.println("검색 결과 없음");
                        continue;
                    }
                }
                //서치 키워드가 0일 경우 (아티클 리스트만 쳤을 경우)
                System.out.println("   번호    /     날짜       /   제목     /    내용   ");
                for (int i = searchkeyList.size() - 1; i >= 0; i--) {
                    Article article = searchkeyList.get(i);
                    if (Util.getOut().split(" ")[0].equals(article.getReDate().split(" ")[0])) {
                        System.out.printf("  %d   /    %s        /    %s     /    %s   \n", article.getId(), article.getReDate().split(" ")[1], article.getTitle(), article.getBody());
                    } else {
                        System.out.printf("  %d   /    %s        /    %s     /    %s   \n", article.getId(), article.getReDate().split(" ")[0], article.getTitle(), article.getBody());
                    }

                }

            }
            else if (cmd.startsWith("article detail")) {
                System.out.println("==게시글 상세보기==");

                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = getArticleById(id);

                if (foundArticle == null) {
                    System.out.println("해당 게시글은 없습니다");
                    continue;
                }
                System.out.println("번호 : " + foundArticle.getId());
                System.out.println("작성날짜 : " + foundArticle.getDate());
                System.out.println("수정날짜 : " + foundArticle.getReDate());
                System.out.println("제목 : " + foundArticle.getTitle());
                System.out.println("내용 : " + foundArticle.getBody());

            }
            else if (cmd.startsWith("article delete")) {
                System.out.println("==게시글 삭제==");

                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = getArticleById(id);

                if (foundArticle == null) {
                    System.out.println("해당 게시글은 없습니다");
                    continue;
                }
                articles.remove(foundArticle);
                System.out.println(id + "번 게시글이 삭제되었습니다");
            }
            else if (cmd.startsWith("article modify")) {
                System.out.println("==게시글 수정==");

                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = getArticleById(id);

                if (foundArticle == null) {
                    System.out.println("해당 게시글은 없습니다");
                    continue;
                }
                System.out.println("기존 제목 : " + foundArticle.getTitle());
                System.out.println("기존 내용 : " + foundArticle.getBody());
                System.out.print("새 제목 : ");
                String newTitle = sc.nextLine().trim();
                System.out.print("새 내용 : ");
                String newBody = sc.nextLine().trim();

                foundArticle.setTitle(newTitle);
                foundArticle.setBody(newBody);
                foundArticle.setReDate(Util.getOut());

                System.out.println(id + "번 게시글이 수정되었습니다");
            }

            else {
                System.out.println("사용할 수 없는 명령어입니다");
            }

        }

        System.out.println("==프로그램 끝==");
        sc.close();
    }

    private static boolean isJoinableLoginId(String loginId) {
        for (Member member : members) {
            if (member.getLoginId().equals(loginId)) {
                return false;
            }
        }
        return true;
    }

    private static Article getArticleById(int id) {
        //실제로 있는 글인지 아닌지 알려주는 메소드
//        for (int i = 0; i < articles.size(); i++) {
//            Article article = articles.get(i);
//            if (article.getId() == id) {
//                return article;
//            }
//        }

        for (Article article : articles) {
            if (article.getId() == id) {
                return article;
            }
        }
        return null;
    }

    /**
     * 테스트 데이터 생성 함수
     **/
    private static void makeTestData() {
        System.out.println("==글 목록 테스트 데이터 생성==");
        articles.add(new Article(1, "2024-12-12 12:12:12", "2024-12-12 12:12:12", "제목123", "내용1"));
        articles.add(new Article(2, Util.getOut(), Util.getOut(), "제목245", "내용2"));
        articles.add(new Article(3, Util.getOut(), Util.getOut(), "제목8753", "내용3"));
    }

    private static void logmakeTestData() {
        System.out.println("==회원 테스트 데이터 생성==");
        members.add(new Member(1, "2024-12-12 00:00:00", "user1", "user1", "회원1"));
        members.add(new Member(1, "2024-12-12 00:00:00", "user2", "user2", "회원2"));
        members.add(new Member(1, "2024-12-12 00:00:00", "user3", "user3", "회원3"));

    }
}

class Member {
    private int id;
    private String regDate;
    private String loginId;
    private String password;
    private String name;

    public Member(int id, String regDate, String loginId, String password, String name) {
        this.id = id;
        this.regDate = regDate;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
}

class Article {
    private int id;
    private String title;
    private String body;
    private String date;
    private String reDate;


    public Article(int id, String date, String reDate, String title, String body) {
        this.id = id;
        this.date = date;
        this.reDate = reDate;
        this.title = title;
        this.body = body;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReDate() {
        return reDate;
    }

    public void setReDate(String reDate) {
        this.reDate = reDate;
    }


}

