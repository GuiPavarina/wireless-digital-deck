package com.puc.wireless.digital.desk.user.controllers.dto;

public class UserInfo {

    private String username;
    private String hash;

    public UserInfo(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "UserInfo [username=" + username + ", hash=" + hash + "]";
    }

}
