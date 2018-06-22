package com.example.admin.final1;

public class Artist {

    private String id;
  
    private String address;

    private String img;

    private String intro;

    private String title;

    public Artist(String id, String address, String img, String intro, String title) {
        this.id = id;
        this.address = address;
        this.img = img;
        this.intro = intro;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getImg() {
        return img;
    }

    public String getIntro() {
        return intro;
    }

    public String getTitle() {
        return title;
    }
}
