package org.example.dto;

public class Article {

    private int id;
    private String reDate;
    private String title;
    private String body;
    private String date;

    private Member member;


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
