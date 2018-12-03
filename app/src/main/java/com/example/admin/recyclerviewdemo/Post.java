package com.example.admin.recyclerviewdemo;

import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable {

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

    protected Post(Parcel in) {
        userProfile = in.readInt();
        userName = in.readString();
        checkIn = in.readString();
        content = in.readString();
        likeCount = in.readString();
        image = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userProfile);
        dest.writeString(userName);
        dest.writeString(checkIn);
        dest.writeString(content);
        dest.writeString(likeCount);
        dest.writeInt(image);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

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

    @Override
    public String toString() {
        return "Post{" +
                "userProfile=" + userProfile +
                ", userName='" + userName + '\'' +
                ", checkIn='" + checkIn + '\'' +
                ", content='" + content + '\'' +
                ", likeCount='" + likeCount + '\'' +
                ", image=" + image +
                '}';
    }
}
