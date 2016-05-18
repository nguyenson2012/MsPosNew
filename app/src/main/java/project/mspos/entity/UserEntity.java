package project.mspos.entity;

/**
 * Created by CONGHAO on 5/5/2016.
 */
public class UserEntity {
    private String username;
    private String password;
    private String displayName;
    private String email;
    private String session;

    public UserEntity() {}
    public UserEntity(int userId, String username, String password, String displayName, String email, String session) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.email = email;
        this.session = session;
    }

    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

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


}
