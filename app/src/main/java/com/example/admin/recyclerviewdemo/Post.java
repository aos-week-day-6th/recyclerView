package com.example.admin.recyclerviewdemo;

public class Post {

    int userProfile;
    String userName;
    String checkIn;
    String content;
    String likeCount;
    int image;

    public Post(int userProfile, String userName, String checkIn, String content, String likeCount, int image) {
        this.userProfile = userProfile;
        this.userName = userName;
        this.checkIn = checkIn;
        this.content = content;
        this.likeCount = likeCount;
        this.image = image;
    }

    public int getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(int userProfile) {
        this.userProfile = userProfile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
