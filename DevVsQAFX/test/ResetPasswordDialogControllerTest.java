import devVsQA.controller.ResetPasswordDialogController;
import devVsQA.model.User;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by bastien on 03/06/2017.
 */
public class ResetPasswordDialogControllerTest {

    // String isResetValid(String oldPasswordField, String newPasswordFieldOne, String newPasswordFieldTwo)

    @Test
    public void should_return_invalid_oldpassword_empty(){
        ResetPasswordDialogController obj = new ResetPasswordDialogController();
        assertThat(obj.isResetValid(null , "Bonjour15", "Bonjour15")).isEqualTo("Ancien mot de passe invalide !\n");
    }

    @Test
    public void should_return_invalid_oldpassword(){
        ResetPasswordDialogController obj = new ResetPasswordDialogController();
        assertThat(obj.isResetValid("bonjour" , "Bonjour15", "Bonjour15")).isEqualTo("Ancient mot de passe invalide !\n");
    }

    public User connectedUser = new User("Farault", "Bastien", "Xenodeux", "faraultbastien@gmail.com", "Motdepasse1", "08/03/1994", "5 rue de truc", "Paris", "75000");

    @Test
    public void should_return_invalid_newpassword1_empty(){

        ResetPasswordDialogController obj = new ResetPasswordDialogController();
        obj.setConnectedUser(connectedUser);
        assertThat(obj.isResetValid("Motdepasse1", null, "Bonjour15")).isEqualTo("Nouveau mot de passe 1 invalide !\n");
    }

    @Test
    public void should_return_invalid_newpassword1(){
        ResetPasswordDialogController obj = new ResetPasswordDialogController();
        obj.setConnectedUser(connectedUser);
        assertThat(obj.isResetValid("Motdepasse1" , "bonjour", "Bonjour15")).isEqualTo("Nouveau mot de passe 1 invalide !\n");
    }

    @Test
    public void should_return_invalid_newpassword2_empty(){
        ResetPasswordDialogController obj = new ResetPasswordDialogController();
        obj.setConnectedUser(connectedUser);
        assertThat(obj.isResetValid("Motdepasse1" , "Bonjour15", null)).isEqualTo("Nouveau mot de passe 2 invalide !\n");
    }

    @Test
    public void should_return_invalid_newpassword2(){
        ResetPasswordDialogController obj = new ResetPasswordDialogController();
        obj.setConnectedUser(connectedUser);
        assertThat(obj.isResetValid("Motdepasse1" , "Bonjour15", "bonjour")).isEqualTo("Nouveau mot de passe 2 invalide !\n");
    }

    @Test
    public void should_return_no_same_newpassword(){
        ResetPasswordDialogController obj = new ResetPasswordDialogController();
        obj.setConnectedUser(connectedUser);
        assertThat(obj.isResetValid("Motdepasse1" , "Bonjour15", "Bonjour16")).isEqualTo("Les nouveaux mots de passe ne correspondent pas !\n");
    }

    @Test
    public void should_return_same_as_oldpassword(){
        ResetPasswordDialogController obj = new ResetPasswordDialogController();
        obj.setConnectedUser(connectedUser);
        assertThat(obj.isResetValid("Motdepasse1" , "Motdepasse1", "Motdepasse1")).isEqualTo("Votre mot de passe doit etre different de \nl'ancient!\n");
    }


}
