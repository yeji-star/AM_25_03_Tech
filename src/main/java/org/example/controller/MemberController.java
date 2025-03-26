package org.example.controller;

import org.example.dto.Member;
import org.example.Util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController extends Controller {
    private Scanner sc;
    private List<Member> members;
    private String cmd;
    private Member loginMenber = null;

    int lastMemberId = 3;

    public MemberController(Scanner sc) {
        this.sc = sc;
        members = new ArrayList<>();
    }

    public void doAction(String cmd, String actionMethodName) {
        this.cmd = cmd;

        switch (actionMethodName) {
            case "join":
                doJoin();
                break;
            case "login":
                doLogin();
                break;
            case "logout":
                doLogout();
                break;
            default:
                System.out.println("Unknown action method");
                break;
        }
    }

    private void doJoin() {
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

    private void doLogin() {
        if (isLogin()) {
            System.out.println("이미 로그인중");
            return;
        }
        System.out.println("==로그인==");

        System.out.print("로그인 아이디 : ");
        String loginId = sc.nextLine().trim();
        System.out.print("비밀번호 : ");
        String password = sc.nextLine().trim();

        // 얘 내 회원인가? -> 사용자가 방금 입력한 로그인 아이디와 일치하는 회원이 나한테 있나?

        Member member = getMemberLoginId(loginId);

        if (member == null) {
            System.out.println("일치하는 회원이 없습니다.");
            return;
        }

        // 있어 -> 내가 알고있는 이 사람의 비번이 지금 얘가 입력한 거랑 같나?

        if (member.getPassword().equals(password) == false) {
            System.out.println("비밀번호가 틀렸습니다.");
            return;
        }

        // 둘 다 통과한 경우
        // 로그인 성공

        loginMenber = member; // 로그인 상태 저장

        System.out.printf("%s님 로그인 성공\n", loginMenber.getName());

    }

    private boolean isLogin() {
        return loginMenber != null;
    }

    private void doLogout() {
        if (!isLogin()) {
            System.out.println("이미 로그아웃중");
            return;
        }

        loginMenber = null;

        System.out.println("로그아웃 되었습니다.");
    }

    private Member getMemberLoginId(String loginId) {
        for (Member member : members) {
            if (member.getLoginId().equals(loginId)) { //찾았으면 멤버를 리턴
                return member;
            }
        }
        return null;
    }


    private boolean isJoinableLoginId(String loginId) {
        for (Member member : members) {
            if (member.getLoginId().equals(loginId)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 테스트 데이터 생성 함수
     **/
    public void makeTestData() {
        System.out.println("==회원 테스트 데이터 생성==");
        members.add(new Member(1, "2024-12-12 00:00:00", "user1", "user1", "회원1"));
        members.add(new Member(1, "2024-12-12 00:00:00", "user2", "user2", "회원2"));
        members.add(new Member(1, "2024-12-12 00:00:00", "user3", "user3", "회원3"));

    }
}
