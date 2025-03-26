package org.example.controller;

import org.example.dto.Member;

import java.util.List;

public class Controller {

    protected static Member loginMember = null;

    protected static List<Member> members;

    public static boolean isLogin() {
        return loginMember != null;
    }

    public void doAction(String cmd, String actionMethodName) {

    }

}
