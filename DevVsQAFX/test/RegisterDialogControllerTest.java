import devVsQA.controller.RegisterDialogController;
import devVsQA.model.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterDialogControllerTest {

    // String isEmailAndPasswordValid(String email, String password);

    @Test
    public void should_return_invalid_mail_and_password(){
        RegisterDialogController obj = new RegisterDialogController();
        assertThat(obj.isEmailAndPasswordValid(null,null)).isEqualTo("Email invalide !\nMot de passe invalide !\n");
    }

    @Test
    public void should_return_invalid_mail(){
        RegisterDialogController obj = new RegisterDialogController();
        assertThat(obj.isEmailAndPasswordValid("ontestepleindechoses", "Bonjour14")).isEqualTo("Email invalide !\n");
    }

    @Test
    public void should_return_invalid_password(){
        RegisterDialogController obj = new RegisterDialogController();
        assertThat(obj.isEmailAndPasswordValid("test@gmail.com", "pasdechiffreetdemaj")).isEqualTo("Mot de passe invalide !\n");
    }

    @Test
    public void should_pass_all(){
        RegisterDialogController obj = new RegisterDialogController();
        assertThat(obj.isEmailAndPasswordValid("test@gmail.com", "Toutestbon14")).isEqualTo("");
    }

    // String isInputValid(String lastname, String firstname, String username, String email, String password, String birthday, String address, String city, String postalCode);

    @Test
    public void should_return_invalid_lastname(){
        RegisterDialogController obj = new RegisterDialogController();
        assertThat(obj.isInputValid(null,"Jean","Jack","test@gmail.com","Capasse14","12/02/1994","la grande rue","Paris","75012")).isEqualTo("Nom invalide !\n");
    }

    @Test
    public void should_return_invalid_lastname_number(){
        RegisterDialogController obj = new RegisterDialogController();
        assertThat(obj.isInputValid("12345","Jean","Jack","test@gmail.com","Capasse14","12/02/1994","la grande rue","Paris","75012")).isEqualTo("Nom invalide !\n");
    }

    @Test
    public void should_return_invalid_firstname(){
        RegisterDialogController obj = new RegisterDialogController();
        assertThat(obj.isInputValid("Phil",null,"Jack","test@gmail.com","Capasse14","12/02/1994","la grande rue","Paris","75012")).isEqualTo("Prenom invalide !\n");
    }

    @Test
    public void should_return_invalid_firstname_number(){
        RegisterDialogController obj = new RegisterDialogController();
        assertThat(obj.isInputValid("Phil","12345","Jack","test@gmail.com","Capasse14","12/02/1994","la grande rue","Paris","75012")).isEqualTo("Prenom invalide !\n");
    }

    @Test
    public void should_return_invalid_username(){
        RegisterDialogController obj = new RegisterDialogController();
        assertThat(obj.isInputValid("Phil","Jean",null,"test@gmail.com","Capasse14","12/02/1994","la grande rue","Paris","75012")).isEqualTo("Pseudo invalide !\n");
    }

    @Test
    public void should_return_invalid_birthday_empty(){
        RegisterDialogController obj = new RegisterDialogController();
        assertThat(obj.isInputValid("Phil","Jean","Jack","test@gmail.com","Capasse14",null,"la grande rue","Paris","75012")).isEqualTo("Date de naissance invalide !\n");
    }

    @Test
    public void should_return_invalid_birthday_letters(){
        RegisterDialogController obj = new RegisterDialogController();
        assertThat(obj.isInputValid("Phil","Jean","Jack","test@gmail.com","Capasse14","azerty","la grande rue","Paris","75012")).isEqualTo("Date de naissance invalide !\n");
    }

    @Test
    public void should_return_invalid_birthday(){
        RegisterDialogController obj = new RegisterDialogController();
        assertThat(obj.isInputValid("Phil","Jean","Jack","test@gmail.com","Capasse14","35/02/1994","la grande rue","Paris","75012")).isEqualTo("Date de naissance invalide !\n");
    }

    @Test
    public void should_return_invalid_address(){
        RegisterDialogController obj = new RegisterDialogController();
        assertThat(obj.isInputValid("Phil","Jean","Jack","test@gmail.com","Capasse14","12/02/1994",null,"Paris","75012")).isEqualTo("Adresse invalide !\n");
    }

    @Test
    public void should_return_invalid_city(){
        RegisterDialogController obj = new RegisterDialogController();
        assertThat(obj.isInputValid("Phil","Jean","Jack","test@gmail.com","Capasse14","12/02/1994","la grande rue",null,"75012")).isEqualTo("Ville invalide !\n");
    }
    @Test
    public void should_return_invalid_city_number(){
        RegisterDialogController obj = new RegisterDialogController();
        assertThat(obj.isInputValid("Phil","Jean","Jack","test@gmail.com","Capasse14","12/02/1994","la grande rue","12345","75012")).isEqualTo("Ville invalide !\n");
    }

    @Test
    public void should_return_invalid_postal(){
        RegisterDialogController obj = new RegisterDialogController();
        assertThat(obj.isInputValid("Phil","Jean","Jack","test@gmail.com","Capasse14","12/02/1994","la grande rue","Paris",null)).isEqualTo("Code postal invalide !\n");
    }

    @Test
    public void should_return_invalid_postal_letters(){
        RegisterDialogController obj = new RegisterDialogController();
        assertThat(obj.isInputValid("Phil","Jean","Jack","test@gmail.com","Capasse14","12/02/1994","la grande rue","Paris","azerty")).isEqualTo("Code postal invalide !\n");
    }

    @Test
    public void should_be_ok(){
        RegisterDialogController obj = new RegisterDialogController();
        assertThat(obj.isInputValid("Phil","Jean","Jack","test@gmail.com","Capasse14","12/02/1994","la grande rue","Paris","75012")).isEqualTo("");
    }

    @Test
    public void should_return_user_already_exist(){
        RegisterDialogController obj = new RegisterDialogController();
        //Some raw data
        List<User> userList = new ArrayList<User>();
        User user1 = new User("Farault", "Bastien", "Xenodeux", "faraultbastien@gmail.com", "Motdepasse1", "08/03/1994", "5 rue de truc", "Paris", "75000");
        User user2 = new User("Fernandes", "Dylan", "Cromade", "fernandesantunesdylan@gmail.com", "Motdepasse2", "06/09/1994", "6 rue de machin", "Paris", "75000");
        User user3 = new User("Guitton", "Candice", "Billy8You", "guittoncandice@gmail.com", "Motdepasse3", "15/08/1992", "7 rue de bidule", "Paris", "75000");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        obj.setUserList(userList);
        assertThat(obj.isInputValid("Farault", "Bastien", "Xenodeux", "faraultbastien@gmail.com", "Motdepasse1", "08/03/1994", "5 rue de truc", "Paris", "75000")).isEqualTo("L'utilisateur existe deja !\n");
    }
}
