package stepdefinitions;

import models.setdata.LoginData;
import models.Constantes;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import static net.serenitybdd.screenplay.GivenWhenThen.*;

import org.hamcrest.Matchers;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import questions.ValidateBusiness;
import tasks.*;
import questions.ValidateLogin;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
public class StartSharpStepDefinitions {
    
    @Before
    public void setStage()
    {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("user in the home page")
    public void userInTheHomePage(DataTable table) {
        //open url
        OnStage.theActorCalled(Constantes.ACTOR).wasAbleTo(OpenBrowser.url());
        //Do login
        OnStage.theActorInTheSpotlight().attemptsTo(Login.credenciales(LoginData.setData(table).get(0)));
        //Validate login
        OnStage.theActorInTheSpotlight().should(seeThat(ValidateLogin.value(),Matchers.equalTo("Dashboard")));
    }

    @When("he is in the dashboard, create the business {string} - {string}")
    public void heIsInTheDashboardCreateTheBusiness(String business, String parenUnit) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Go.toBusinessUnit(),
                Business.create(business,parenUnit)
        );
    }

    @Then("he verifies that the business {string} this created")
    public void heVerifiesThatTheBusinessThisCreated(String business) {
        OnStage.theActorInTheSpotlight().should(seeThat(ValidateBusiness.name(business),Matchers.equalTo(true)));
        OnStage.theActorInTheSpotlight().attemptsTo(Go.toLoginPage());
    }

    @When("he is in the dashboard, create the meeting for {string}")
    public void heIsInTheDashboardCreateTheMeetingFor(String arg0) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Go.toMeetings()
        );
    }
}