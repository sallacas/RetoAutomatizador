package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;

public class OpenBrowser implements Task
{
    public OpenBrowser() { /* Constructor empty */ }

    public static OpenBrowser url()
    {
        return Tasks.instrumented(OpenBrowser.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor)
    {
        actor.attemptsTo(
                Open.url("https://serenity.is/demo/")
        );
    }
}


       
