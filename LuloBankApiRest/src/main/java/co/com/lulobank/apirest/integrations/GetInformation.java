package co.com.lulobank.apirest.integrations;

import co.com.lulobank.apirest.exceptions.FailedOfApi;
import co.com.lulobank.apirest.models.Employee;
import co.com.lulobank.apirest.util.Resources;
import io.restassured.path.json.exception.JsonPathException;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

import java.util.List;

public class GetInformation implements Task {

    public static GetInformation ofEmployees() {
        return Tasks.instrumented(GetInformation.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Get.resource(Resources.RESOURCE_SEARCH.toString())
        );
        try {
            List<Employee> employees = SerenityRest.lastResponse().jsonPath().getList("data", Employee.class);
            actor.remember("employees", employees);
            actor.remember("statusCode", SerenityRest.lastResponse().getStatusCode());
            actor.remember("statusMessage", SerenityRest.lastResponse().jsonPath().getString("status"));
            actor.remember("message", SerenityRest.lastResponse().jsonPath().getString("message"));
        } catch (JsonPathException fapi) {
            throw new FailedOfApi("Failed when trying to search all employees and the code is: " + SerenityRest.lastResponse().getStatusCode(), fapi);
        }
    }
}
