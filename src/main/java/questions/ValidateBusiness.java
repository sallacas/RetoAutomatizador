package questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.actions.ScrollTo;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.Displayed;
import net.serenitybdd.screenplay.waits.Wait;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.WebDriver;
import userinterfaces.BusinessPage;

public class ValidateBusiness implements Question<Boolean> {

    private final String name;

    public ValidateBusiness(String name){
        this.name = name;
    }
    public static Question<Boolean> name(String name) {
        return new ValidateBusiness(name);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        actor.attemptsTo(
                Click.on(BusinessPage.BTN_REFRESH),
                WaitUntil.the(BusinessPage.businessLink(name), WebElementStateMatchers.isVisible()),
                Scroll.to(BusinessPage.businessLink(name)).andAlignToBottom()
        );
        return actor.asksFor(Displayed.of(BusinessPage.businessLink(name)));
    }
}
