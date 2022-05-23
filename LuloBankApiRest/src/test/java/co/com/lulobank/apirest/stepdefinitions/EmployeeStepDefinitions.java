package co.com.lulobank.apirest.stepdefinitions;


import co.com.lulobank.apirest.exceptions.ResponseValidation;
import co.com.lulobank.apirest.integrations.CreateEmployee;
import co.com.lulobank.apirest.integrations.DeleteEmployee;
import co.com.lulobank.apirest.integrations.GetEmployee;
import co.com.lulobank.apirest.integrations.GetInformation;
import co.com.lulobank.apirest.models.Employee;
import co.com.lulobank.apirest.questions.*;
import co.com.lulobank.apirest.util.Resources;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class EmployeeStepDefinitions {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^that (.*) wants to call the api$")
    public void thatTheActorWantsToCallTheApi(String actor) {
        OnStage.theActorCalled(actor).whoCan(CallAnApi.at(Resources.END_POINT.toString()));
    }

    @When("^he get all employees$")
    public void heGetAllEmployees() {
        theActorInTheSpotlight().attemptsTo(GetInformation.ofEmployees());
    }

    @When("^he create a new employee$")
    public void heCreateANewEmployee(List<Employee> employees) {
        theActorInTheSpotlight().attemptsTo(CreateEmployee.with(employees.get(0)));
    }

    @When("^he search the employee with (.*)$")
    public void heSearchTheEmployeeWith(String id) {
        theActorInTheSpotlight().attemptsTo(GetEmployee.byId(id));
    }

    @When("^he delete the employee with (.*)$")
    public void heDeleteTheEmployeeWith(String id) {
        theActorInTheSpotlight().attemptsTo(DeleteEmployee.byId(id));
    }

    @Then("^he see the status message is (.*)")
    public void heSeeTheStatusMessageIsSuccess(String message) {
        theActorInTheSpotlight().should(seeThat(StatusMessage.ofResource(),equalTo(message))
                .orComplainWith(ResponseValidation.class, "error validating status message of response")
        );
    }
    @Then("^he see the status code (.*)$")
    public void heSeeTheStatusCode(String statusCode) {
        theActorInTheSpotlight().should(seeThat(StatusCode.ofResource(),equalTo(statusCode))
                .orComplainWith(ResponseValidation.class, "error validating status code of response")
        );
    }

    @Then("^he see the message (.*)$")
    public void heSeeTheMessage(String message) {
        theActorInTheSpotlight().should(seeThat(Messaje.ofResource(),equalTo(message))
                .orComplainWith(ResponseValidation.class, "error validating message of response")
        );
    }

    @Then("^he see the attribute (.*) is not empty$")
    public void heSeeTheAttributeIdNot(String attribute) {
        theActorInTheSpotlight().should(seeThat(Attribute.hasValue(attribute),is(true))
                .orComplainWith(ResponseValidation.class, "error validating attribute of response")
        );
    }

    @Then("he see the employee with data")
    public void heSeeTheEmployeeWithData(List<Employee> employees) {
        if(employees.get(0).getProfileImage()==null) employees.get(0).setProfileImage("");

        theActorInTheSpotlight().should(seeThat(Body.is(),equalTo(employees.get(0).toString()))
                .orComplainWith(ResponseValidation.class, "error validating data of response")
        );
    }
}
