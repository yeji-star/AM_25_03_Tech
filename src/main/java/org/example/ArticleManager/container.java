package org.example.ArticleManager;

import org.example.dao.ArticleDao;
import org.example.dao.MemberDao;


//저장소 먼저 만들어 놓고 시작하겠다
public class container {
    public static ArticleDao articleDao;
    public static MemberDao memberDao;

    static {
        articleDao = new ArticleDao();
        memberDao = new MemberDao();
    }
}
