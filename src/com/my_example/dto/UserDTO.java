package com.my_example.dto;

public class UserDTO {
    private String userid;
   
    public String getUserid() {
        return userid;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    private String password;
    public void setUserid(String userid)
    {
        this.userid=userid;
    }
    

}
