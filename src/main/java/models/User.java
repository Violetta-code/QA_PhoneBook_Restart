package models;

public class User {

    private String email;
    private String password;

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public User withEmail(String email) {
        //withEmail равен setEmail и  возвращает нам User а не воид
       // public User setEmail(String email) {
       //   this.email = email;
       //    return this;
       // }
        this.email = email;
        return this;
    }

    public User withPassword(String password) {
       // public User setPassword(String password) {
       //    this.password = password;
       //     return this;
       // }
        this.password = password;
        return this;
    }
}
