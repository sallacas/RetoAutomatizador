package tasks;

import models.setdata.*;
import userinterfaces.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.*;

public class Login implements Task
{
    private LoginData loginData;
    
    public Login(LoginData loginData)
    {
        this.loginData = loginData;
    }

    public static Login credenciales(LoginData loginData)
    {
        return Tasks.instrumented(Login.class,loginData);
    }

    @Override
    public <T extends Actor> void performAs(T actor)
    {
        actor.attemptsTo(
            Enter.theValue(loginData.getUser()).into(LoginPage.TXT_USER),
            Enter.theValue(loginData.getPassword()).into(LoginPage.TXT_PASSWORD),
            Click.on(LoginPage.BTN_SIGN_IN));
    }
}


       
