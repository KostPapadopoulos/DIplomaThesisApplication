package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model;

// TODO We can add a GUEST role, which can only see the available thesis and do nothing else

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@Entity
@Table(name = "USER_")
public class User implements UserDetails {

    @Id
    @Column (name = "us_id")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int us_id;

    @Column (name = "username", unique = true)
    private String username;

    @Column (name = "password_")
    private String password;

    @Enumerated (EnumType.STRING)
    @Column (name = "role_")
    private Role role;

    public User() {}

    public User(String userName, String password, Role role) {
        this.username = userName;
        this.password = password;
        this.role = role;
    }

    public User(int us_id, String userName, String password, Role role){
        this.us_id = us_id;
        this.username = userName;
        this.password = password;
        this.role = role;
    }


    // TODO Professor acc has to enter a specific PIN in order to create his account
    /*
    public void createAccount(String userName, String password, Role theRole){
        new User(userName,password);
        this.role = theRole;
    }
    */
    // TODO We have to create getAuthority() which extends GrantedAuthority (maybe a class containing all authorities ie selectThesis)


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority((role.name()));
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return username;
    }

    public boolean isAccountNonExpired() {
        // if account is non expired return true
        // else false
        return true;
    }

    public boolean isAccountNonLocked() {
        // if account is non locked return true
        // else false
        return true;
    }

    public boolean isCredentialsNonExpired() {
        // if credentials are not expired return true
        // else false
        return true;
    }

    public boolean isEnabled() {
        // if enabled return true
        return true;
    }

}