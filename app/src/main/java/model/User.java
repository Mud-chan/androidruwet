package model;

public class User {

    private int user_id;
    private String user_email;
    private String user_password;
    private String user_fullname;

    public User() {

    }

    public User(int user_id, String user_email, String user_password, String user_fullname) {
        this.user_id = user_id;
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_fullname = user_fullname;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_fullname() {
        return user_fullname;
    }

    public void setUser_fullname(String user_fullname) {
        this.user_fullname = user_fullname;
    }
}
