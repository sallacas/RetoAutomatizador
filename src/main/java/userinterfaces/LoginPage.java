package userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginPage {
    LoginPage() {
        // Do nothing because of X and Y.
    }

    public static final Target TXT_USER = Target.the("username").located(By.name("Username"));
    public static final Target TXT_PASSWORD = Target.the("password").located(By.name("Password"));
    public static final Target BTN_SIGN_IN = Target.the("Sign in button")
            .located(By.id("LoginPanel0_LoginButton"));
}

