package com.company;

import com.sun.istack.internal.Nullable;

public class User {

    static User currentUser;

    private Integer id;
    private String email;
    private String name;
    private String password;
    private String lastLoginDate;

    public User(Integer id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public User(Integer id, String email, String name, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
