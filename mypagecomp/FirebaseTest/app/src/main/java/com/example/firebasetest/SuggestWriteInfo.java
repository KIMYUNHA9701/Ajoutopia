package com.example.firebasetest;

public class SuggestWriteInfo {
    private String title;
    private String contents;
    private String publisher;
    private int point;

    public SuggestWriteInfo(String title, String contents, String publisher) {
        this.title = title;
        this.contents = contents;
        this.publisher = publisher;
    }
    public SuggestWriteInfo(String title, String contents, String publisher, int point) {
        this.title = title;
        this.contents = contents;
        this.publisher = publisher;
        this.point = point;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
