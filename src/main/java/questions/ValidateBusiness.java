package questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.questions.Displayed;
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
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        driver.navigate().refresh();
        return actor.asksFor(Displayed.of(BusinessPage.businessLink(name)));
    }
}
