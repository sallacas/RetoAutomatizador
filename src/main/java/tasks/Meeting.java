package tasks;

import models.setdata.MeetingData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.*;
import org.openqa.selenium.Keys;
import userinterfaces.MeetingPage;

public class Meeting implements Task {

    private MeetingData data;
    private String[] attendees = {"Allison", "Brian", "Emily", "Joseph"};

    public Meeting(MeetingData data) {
        this.data = data;
    }

    public static Meeting createWith(MeetingData meetingData) {
        return Tasks.instrumented(Meeting.class, meetingData);
    }

    public static Performable saveMeeting() {
        return Task.where("{Click} on button to save the meeting",
                Click.on(MeetingPage.BTN_SAVE)
        );
    }

    public static Performable refreshMeetings() {
        return Task.where("{Click} on button to refresh meetings",
                Click.on(MeetingPage.BTN_REFRESH)
        );
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        //Fill data of meeting
        actor.attemptsTo(
                //Insert meeting info
                SendKeys.of(data.getTypeMeeting() + " " + data.getNameBusiness()).into(MeetingPage.TXT_MEETING_NAME),
                Click.on(MeetingPage.dropDownInputText("MeetingTypeId")).then(
                        SendKeys.of(data.getTypeMeeting()).into(MeetingPage.TXT_SEARCH_DROPDOWN).thenHit(Keys.ENTER)
                ),
                SendKeys.of(data.getNumber()).into(MeetingPage.TXT_MEETING_NUMBER),
                //Insert date - hour data
                Clear.field(MeetingPage.TXT_START_DATE).then(
                        SendKeys.of(data.getDate()).into(MeetingPage.TXT_START_DATE)
                ),
                Clear.field(MeetingPage.TXT_END_DATE).then(
                        SendKeys.of(data.getDate()).into(MeetingPage.TXT_END_DATE)
                ),
                SelectFromOptions.byValue(data.getHourStart()).from(MeetingPage.selectFor("StartDate")),
                SelectFromOptions.byValue(data.getHourEnd()).from(MeetingPage.selectFor("EndDate")),
                //Select Location and Unit
                Click.on(MeetingPage.dropDownInputText("LocationId")).then(
                        SendKeys.of(data.getLocation()).into(MeetingPage.TXT_SEARCH_DROPDOWN).thenHit(Keys.ENTER)
                ),
                Click.on(MeetingPage.dropDownInputText("UnitId")).then(
                        SendKeys.of(data.getNameBusiness()).into(MeetingPage.TXT_SEARCH_DROPDOWN).thenHit(Keys.ENTER)
                ),
                //Select host and reporter
                Click.on(MeetingPage.dropDownInputText("OrganizerContactId")).then(
                        SendKeys.of(data.getHost()).into(MeetingPage.TXT_SEARCH_DROPDOWN).thenHit(Keys.ENTER)
                ),
                Click.on(MeetingPage.dropDownInputText("ReporterContactId")).then(
                        SendKeys.of(data.getReporter()).into(MeetingPage.TXT_SEARCH_DROPDOWN).thenHit(Keys.ENTER)
                )
        );
        //Add attendees
        for (String person: attendees) {
            actor.attemptsTo(
                    Click.on(MeetingPage.DROPDOWN_ATTENDEES).then(
                            SendKeys.of(person).into(MeetingPage.TXT_SEARCH_DROPDOWN).thenHit(Keys.ENTER)
                    )
            );
        }
    }
}
