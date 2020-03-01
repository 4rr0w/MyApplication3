package com.example.myapplication.Models;

public class cards {
    private String userId;
    private String name;
    private String profileImageUrl;
    public cards(String userid,String name,String profileImageUrl)
    {
        this.userId=userid;
        this.name=name;
        this.profileImageUrl=profileImageUrl;
    }
    public String getUserId()
    {
        return userId;
    }
    public void setUserId(String userid)
    {
        this.userId=userid;
    }
    public String getName()
    {
        return name;
    }
    public void setName()
    {
        this.name=name;
    }
    public String getProfileImageUrl()
    {
        return profileImageUrl;
    }
    public void setprofileImageUrl()
    {
        this.profileImageUrl=profileImageUrl;
    }
}
