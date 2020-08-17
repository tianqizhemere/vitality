package top.tianqi.vitality.entity;

import java.io.Serializable;

/**
 * 用户实体类
 *
 * @Author wkh
 * @Date 2020/8/17 15:40
 */
public class AuthUser implements Serializable {

    private static final long serialVersionUID = 1192417861258357400L;

    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
    /** 账号是否过期 */
    private boolean accountNonExpired = true;
    /** 账户是否未锁定 */
    private boolean accountNonLocked= true;
    /** 用户凭证是否没过期 */
    private boolean credentialsNonExpired= true;
    /** 用户是否可用 */
    private boolean enabled= true;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
