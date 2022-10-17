package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.*;
import org.openqa.selenium.Keys;
import userinterfaces.BusinessPage;

public class Business implements Task {

    private final String business;
    private final String parentUnit;

    public Business(String business, String parentUnit){
        this.business = business;
        this.parentUnit = parentUnit;
    }

    public static Business create(String business, String parentUnit) {
        return Tasks.instrumented(Business.class,business,parentUnit);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(BusinessPage.btnName("New Business Unit")),
                SendKeys.of(business).into(BusinessPage.TXT_NAME_BUSINESS),
                Click.on(BusinessPage.DROPDOWN_PARENT_UNIT).then(
                    SendKeys.of(parentUnit).into(BusinessPage.TXT_PARENT_UNIT).thenHit(Keys.ENTER)
                ),
                Click.on(BusinessPage.btnName("Save")),
                Click.on(BusinessPage.BTN_REFRESH)
        );
    }
}
