package questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.questions.Displayed;
import org.openqa.selenium.WebDriver;
import userinterfaces.MeetingPage;


public class ValidateMeeting implements Question<Boolean> {

    private String nameMeeting;

    public ValidateMeeting(String nameMeeting){
        this.nameMeeting = nameMeeting;
    }
    public static Question<Boolean> isCreated(String nameMeeting) {
        return new ValidateMeeting(nameMeeting);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return actor.asksFor(Displayed.of(MeetingPage.meetingLink(nameMeeting)));
    }
}
