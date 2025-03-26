package org.example.dto;

public class Article extends Dto{

    private String title;
    private String body;
    private String date;

    private int memberId;

    public Article(int id, String date, String reDate, int memberId, String title, String body) {
        this.id = id;
        this.date = date;
        this.reDate = reDate;
        this.memberId = memberId;
        this.title = title;
        this.body = body;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
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
