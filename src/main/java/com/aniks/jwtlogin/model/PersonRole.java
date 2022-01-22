package com.aniks.jwtlogin.model;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.aniks.jwtlogin.model.AppUserPermission.*;

public enum PersonRole {
    PREMIUM(Sets.newHashSet(BLOG_READ, TRAINING_READ)),
    ADMIN(Sets.newHashSet(USER_WRITE, USER_WRITE, AUTHOR_READ, AUTHOR_WRITE, BLOG_READ, BLOG_WRITE, TRAINING_WRITE, TRAINING_READ)),
    ADMINTRAINEE(Sets.newHashSet(TRAINING_WRITE, TRAINING_READ));

    private final Set<AppUserPermission> permissions;

    PersonRole(Set<AppUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<AppUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority((permission.getPermission())))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
