package userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class DashboardPage {

    public static final Target LINK_LOG_OUT = Target.the("Link to log out from site")
            .located(By.cssSelector("div.dropend>div>a[href='/demo/Account/Signout']"));

    DashboardPage(){
        //Do nothing cause X and Y
    }
    //Content Header
    public static final Target LBL_TITLE = Target.the("content header")
            .located(By.cssSelector("section.content-header>h1"));
    public static Target navMenu(String option){
        return Target.the(option+" nav option")
            .located(By.xpath("//*[@class='s-sidebar-link-text' and text()='"+option+"']//parent::a"));
    }
    public static final Target ADMIN_SECTION = Target.the("Admin section")
            .located(By.cssSelector("div.dropend>a[title='admin']"));
}
