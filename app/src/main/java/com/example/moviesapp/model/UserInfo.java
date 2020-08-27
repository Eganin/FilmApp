package com.example.moviesapp.model;

import java.util.ArrayList;

public class UserInfo {
    private String nameSearch;
    private int id;

    public UserInfo() {

    }

    public UserInfo(int id, String nameSearch) {
        this.id = id;
        this.nameSearch = nameSearch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
