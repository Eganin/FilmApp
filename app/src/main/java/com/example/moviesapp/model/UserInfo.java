package com.example.moviesapp.model;

public class UserInfo {
    private String nameSearch;

    public UserInfo() {

    }

    public UserInfo(String nameSearch) {
        this.nameSearch = nameSearch;
    }

    public String getNameSearch() {
        return nameSearch;
    }

    public void setNameSearch(String nameSearch) {
        this.nameSearch = nameSearch;
    }
}
