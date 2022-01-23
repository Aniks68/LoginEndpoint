package com.aniks.jwtlogin.model;

public enum AppUserPermission {
//    USER_READ("user:read"),
//    USER_WRITE("user:write"),
//    AUTHOR_READ("author:read"),
//    AUTHOR_WRITE("author:write"),
//    BLOG_READ("blog:read"),
//    BLOG_WRITE("blog:write"),
//    TRAINING_READ("training:read"),
//    TRAINING_WRITE("training:write");
//
//    private final String permission;
//
//    AppUserPermission(String permission) {
//        this.permission = permission;
//    }
//
//    public String getPermission() {
//        return permission;
//    }

    USER_READ("user:read"),
    USER_WRITE("user:write"),
    AUTHOR_READ("author:read"),
    AUTHOR_WRITE("author:write"),
    TRAINING_READ("training:read"),
    TRAINING_WRITE("training:write"),
    BLOG_WRITE("blog:write"),
    BLOG_READ("blog:read");

    private String permission;

    AppUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
