package userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class BusinessPage {

    BusinessPage(){
        //Do nothing
    }
    public static Target btnName(String name){
        return Target.the("Button "+name).located(By.xpath("//span[text()=' "+name+"']"));
    }
    public static Target businessLink(String name){
        return Target.the("Link of the business "+name)
                .located(By.xpath("//*[@data-item-type='Pro.Organization.BusinessUnit' and text()='"+name+"']"));
    }
    public static final Target TXT_NAME_BUSINESS = Target.the("Input text for name business")
            .located(By.name("Name"));
    public static final Target DROPDOWN_PARENT_UNIT = Target.the("Dropdown of Parent Unit content")
            .located(By.cssSelector("div.field.ParentUnitId>div>a"));
    public static final Target TXT_PARENT_UNIT = Target.the("Input text for find Parent Unit")
            .located(By.cssSelector("input[role='combobox']"));
    public static final Target BTN_REFRESH = Target.the("Button to refresh business units")
            .located(By.cssSelector("span.button-inner > i.fa-refresh"));

}
