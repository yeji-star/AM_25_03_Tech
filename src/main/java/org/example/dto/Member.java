package org.example.dto;

public class Member extends Dto {

    private String loginId;
    private String password;
    private String name;

    public Member(int id, String reDate, String loginId, String password, String name) {
        this.id = id;
        this.reDate = reDate;
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
        return reDate;
    }

    public void setRegDate(String regDate) {
        this.reDate = regDate;
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
