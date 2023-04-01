package diplomas_mgt_app_skeleton.model;

// TODO We can add a GUEST role, which can only see the available thesis and do nothing else

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.Collection;

@Getter
@Setter
public class User {
    private String userName;
    private String password;
    private Role role;

    public User(String userName, String password, Role role) {
        this.userName = userName;
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

    public Collection<? extends GrantedAuthority> getAuthority() {}
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