package org.example.ArticleManager;

import org.example.dao.ArticleDao;
import org.example.dao.MemberDao;
import org.example.sv.ArticleService;
import org.example.sv.MemberService;


//저장소 먼저 만들어 놓고 시작하겠다
public class container {
    public static ArticleDao articleDao;
    public static MemberDao memberDao;

    public static ArticleService articleService;
    public static MemberService memberService;

    public static void init() {
        articleDao = new ArticleDao();
        memberDao = new MemberDao();

        memberService = new MemberService();
        articleService = new ArticleService();
    }
}
