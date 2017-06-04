import devVsQA.controller.ConnectionDialogController;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by bastien on 03/06/2017.
 */
public class ConnectionDialogControllerTest {

    // String isConnectionValueValid(String email, String password);

    @Test
    public void should_return_invalid_mail_empty (){
        ConnectionDialogController obj = new ConnectionDialogController();
        assertThat(obj.isConnectionValueValid(null,"Bonjour14")).isEqualTo("Email invalide !\n");
    }

    @Test
    public void should_return_invalid_mail (){
        ConnectionDialogController obj = new ConnectionDialogController();
        assertThat(obj.isConnectionValueValid("aqsxcghn","Bonjour14")).isEqualTo("Email invalide !\n");
    }

    @Test
    public void should_return_invalid_password (){
        ConnectionDialogController obj = new ConnectionDialogController();
        assertThat(obj.isConnectionValueValid("test@gmail.com",null)).isEqualTo("Mot de passe invalide !\n");
    }

    @Test
    public void should_return_invalid_password_empty (){
        ConnectionDialogController obj = new ConnectionDialogController();
        assertThat(obj.isConnectionValueValid("test@gmail.com","bonjour")).isEqualTo("Mot de passe invalide !\n");
    }

    @Test
    public void should_be_ok (){
        ConnectionDialogController obj = new ConnectionDialogController();
        assertThat(obj.isConnectionValueValid("test@gmail.com","Bonjour14")).isEqualTo("");
    }
}
