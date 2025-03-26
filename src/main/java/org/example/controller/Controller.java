package org.example.controller;

import org.example.dto.Member;

public class Controller {

    protected static Member loginMember = null;

    public static boolean isLogin() {
        return loginMember != null;
    }

    public void doAction(String cmd, String actionMethodName) {

    }

}
