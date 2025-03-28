package org.example.sv;

import org.example.ArticleManager.container;
import org.example.dao.MemberDao;
import org.example.dto.Member;

import java.util.List;

public class MemberService {

    private MemberDao memberDao;

    public MemberService(){
        memberDao = container.memberDao; //굳이 이걸 쓰지 않고 리스트 안에 리턴값을 container.~~ 으로 해도 됨.
    }

    public List<Member> getMembers() {
        return memberDao.members; // dao 에 잇는 멤버스를 가져다 준다.
    }
}
