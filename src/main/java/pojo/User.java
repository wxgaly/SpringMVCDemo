package pojo;

/**
 * pojo.User
 *
 * @author Created by WXG on 2018/5/15 015 13:56.
 * @version V1.0
 */

public class User {

    private String id;
    private String username;

    public User() {
    }

    public User(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
