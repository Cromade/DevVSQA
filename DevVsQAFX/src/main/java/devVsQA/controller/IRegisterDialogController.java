package devVsQA.controller;

public interface IRegisterDialogController {

    String isEmailAndPasswordValid(String email, String password);

    String isInputValid(String lastname, String firstname, String username, String email, String password, String birthday, String address, String city, String postalCode);

}
