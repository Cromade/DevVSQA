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

    public boolean equals(String lastnameTmp, String firstnameTmp, String usernameTmp, String emailTmp,
                          String passwordTmp, String birthdayTmp, String addressTmp, String cityTmp, String postalCodeTmp) {
        if (!lastname.equals(lastnameTmp)) {
            return false;
        }
        if (!firstname.equals(firstnameTmp)) {
            return false;
        }
        if (!username.equals(usernameTmp)) {
            return false;
        }
        if (!email.equals(emailTmp)) {
            return false;
        }
        if (!password.equals(passwordTmp)) {
            return false;
        }
        if (!birthday.equals(birthdayTmp)) {
            return false;
        }
        if (!address.equals(addressTmp)) {
            return false;
        }
        if (!city.equals(cityTmp)) {
            return false;
        }
        if (!postalCode.equals(postalCodeTmp)) {
            return false;
        }
        return true;
    }
}
