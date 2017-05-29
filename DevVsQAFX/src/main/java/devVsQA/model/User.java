package devVsQA.model;

public class User {

    private String lastname;
    private String firstname;
    private String username;
    private String email;
    private String password;
    private String birthday;
    private String address;
    private String city;
    private String postalCode;

    public User(String lastname, String firstname, String username, String email,
                String password, String birthday, String address, String city, String postalCode) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
