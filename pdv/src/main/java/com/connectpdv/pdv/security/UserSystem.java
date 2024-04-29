package com.connectpdv.pdv.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;


public class UserSystem extends User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String userNameApplication;

    public UserSystem(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userNameApplication = username;
    }

    public String getUserNameApplication() {
        return userNameApplication;
    }

    public void setUserNameApplication(String userNameApplication) {
        this.userNameApplication = userNameApplication;
    }

}
