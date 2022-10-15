package tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import userinterfaces.DashboardPage;

public class Go {

    private Go() {
        throw new IllegalStateException("utility class");
    }

    public static Performable toBusinessUnit() {
        return Task.where("{Navigate} to Business Section",
                Click.on(DashboardPage.navMenu("Organization")),
                Click.on(DashboardPage.navMenu("Business Units"))
        );
    }

    public static Performable toLoginPage() {
        return Task.where("{Log out} from the site",
                Click.on(DashboardPage.ADMIN_SECTION),
                Click.on(DashboardPage.LINK_LOG_OUT)
        );
    }

    public static Performable toMeetings() {
        return Task.where("{Navigate} to Meetings",
                Click.on(DashboardPage.navMenu("Meeting")),
                Click.on(DashboardPage.navMenu("Meetings"))
        );
    }
}
