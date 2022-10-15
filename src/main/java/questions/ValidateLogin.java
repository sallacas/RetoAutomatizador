package questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import userinterfaces.*;

public class ValidateLogin implements Question<String> {
    public static Question<String> value() { return new ValidateLogin(); }

    @Override
    public String answeredBy(Actor actor)
    {
        return Text.of(DashboardPage.LBL_TITLE).answeredBy(actor);
    }
}

