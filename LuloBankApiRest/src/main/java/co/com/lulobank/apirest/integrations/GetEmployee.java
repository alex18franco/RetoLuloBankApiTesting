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

public class GetEmployee implements Task {

    private String id;

    public GetEmployee(String id) {
        this.id = id;
    }

    public static GetEmployee byId(String id) {
        return Tasks.instrumented(GetEmployee.class, id);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Get.resource(Resources.RESOURCE_SEARCH_BY + id)
        );
        try {
            Employee employee = SerenityRest.lastResponse().jsonPath().getObject("data", Employee.class);
            actor.remember("bodyEmployee", employee);
            actor.remember("statusCode", SerenityRest.lastResponse().getStatusCode());
            actor.remember("statusMessage", SerenityRest.lastResponse().jsonPath().getString("status"));
            actor.remember("message", SerenityRest.lastResponse().jsonPath().getString("message"));
        } catch (JsonPathException fapi) {
            throw new FailedOfApi("Failed when trying to search employee with id " + id + " and the code is: " + SerenityRest.lastResponse().getStatusCode(), fapi);
        }
    }
}
