package org.example.ArticleManager;

import org.example.dto.Article;
import org.example.controller.ArticleController;
import org.example.controller.Controller;
import org.example.controller.MemberController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    List<Article> articles;


    public App() { // 생성자
        articles = new ArrayList<>();
    }

    public static void run() {

        Scanner sc = new Scanner(System.in);

        System.out.println("==프로그램 시작==");

        MemberController memberController = new MemberController(sc);
        ArticleController articleController = new ArticleController(sc);


        articleController.makeTestData();
        memberController.makeTestData();

        Controller controller = null;

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

            String[] cmdBits = cmd.split(" ");

            String controllerName = cmdBits[0];

            if (cmdBits.length == 1) {
                System.out.println("명령어 확인 필요");
                continue;
            }

            String actionMethodName = cmdBits[1];

            String forLoginCheck = controllerName + "/" + actionMethodName;

            switch (forLoginCheck) {
                case "article/write":
                case "article/delete":
                case "article/modify":
                case "article/logout":
                    if (Controller.isLogin() == false) {
                        System.out.println("로그인 하세요.");
                        continue;
                    }
                    break;
            }

            switch (forLoginCheck) {
                case "member/login":
                    case "member/join":
                        if (Controller.isLogin()) {
                            System.out.println("로그아웃 하세요.");
                            continue;
                        }
                        break;
            }

            if (controllerName.equals("article")) {
                controller = articleController;
            } else if (controllerName.equals("member")) {
                controller = memberController;
            } else {
                System.out.println("지원하지 않는 기능입니다.");
                continue;
            }

            controller.doAction(cmd, actionMethodName);

//            //do와 show의 차이 : 데이터베이스에 영향을 미치냐 안미치냐. 저장소에 영향을 주냐 안주냐.
//            if (cmd.equals("member join")) {
//                memberController.doJoin();
//            } else if (cmd.equals("article write")) {
//                articleController.doWrite();
//            } else if (cmd.startsWith("article list")) {
//                articleController.showList(cmd);
//            } else if (cmd.startsWith("article detail")) {
//                articleController.showDetail(cmd);
//            } else if (cmd.startsWith("article delete")) {
//                articleController.doDelete(cmd);
//            } else if (cmd.startsWith("article modify")) {
//                articleController.doModi(cmd);
//            } else {
//                System.out.println("사용할 수 없는 명령어입니다");
//            }

        }
        System.out.println("==프로그램 끝==");
        sc.close();
    }
}
