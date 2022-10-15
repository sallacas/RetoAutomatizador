package userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class MeetingPage {
    MeetingPage(){
        //Do nothing
    }
    public static final Target DROPDOWN_ATTENDEES = Target.the("List of Attendees")
            .located(By.xpath("//div[@class='field AttendeeList']//a[@href]"));
    public static final Target BTN_SAVE = Target.the("Save meeting")
            .located(By.xpath("//span[contains(text(),'Save')]"));
    public static Target dropDownInputText(String id){
        return Target.the("Dropdown for types meeting")
                .located(By.cssSelector("div."+id+">div>a"));
    }
    public static final Target TXT_MEETING_NAME = Target.the("Input text for meeting name")
            .located(By.name("MeetingName"));
    public static final Target TXT_SEARCH_DROPDOWN = Target.the("Input text for search in dropdowns")
            .located(By.xpath("//div[@id='select2-drop']/div/input"));
    public static final Target TXT_MEETING_NUMBER = Target.the("Input text for meeting number")
            .located(By.name("MeetingNumber"));
    public static final Target TXT_START_DATE = Target.the("Input text for start date of meeting")
            .located(By.name("StartDate"));
    public static final Target TXT_END_DATE = Target.the("Input text for end date of meeting")
            .located(By.name("EndDate"));

    public static Target selectFor(String id){
        return Target.the("Select for start hour of meeting")
                .located(By.cssSelector("div."+id+">select"));
    }
    public static final Target BTN_REFRESH = Target.the("Button to refresh business units")
            .located(By.cssSelector("span.button-inner > i.fa-refresh"));

    public static Target meetingLink(String nameMeeting) {
        return Target.the("Link of meeting "+nameMeeting)
                .located(By.xpath("//*[@data-item-type='Pro.Meeting.Meeting' and text()='"+nameMeeting+"']"));
    }
}
