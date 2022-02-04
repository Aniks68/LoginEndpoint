package com.aniks.jwtlogin.model;

public enum AppUserPermission {

    USER_READ("user:read"),
    USER_WRITE("user:write"),
    AUTHOR_READ("author:read"),
    AUTHOR_WRITE("author:write"),
    TRAINING_READ("training:read"),
    TRAINING_WRITE("training:write"),
    PRODUCT_WRITE("product:write"),
    PRODUCT_READ("product:read"),
    USERS_WRITE("users:write"),
    BLOG_WRITE("blog:write"),
    USERS_READ("users:read"),
    BLOG_READ("blog:read");

    private String permission;

    AppUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
