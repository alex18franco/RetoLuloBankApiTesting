package co.com.lulobank.apirest.integrations;

import co.com.lulobank.apirest.exceptions.FailedOfApi;
import co.com.lulobank.apirest.models.Employee;
import co.com.lulobank.apirest.util.Resources;
import io.restassured.http.ContentType;
import io.restassured.path.json.exception.JsonPathException;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;


public class CreateEmployee implements Task {

    private Employee employee;

    public CreateEmployee(Employee employee) {
        this.employee = employee;
    }

    public static CreateEmployee with(Employee employee) {
        return Tasks.instrumented(CreateEmployee.class, employee);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(Resources.RESOURCE_CREATE.toString())
                        .with(request -> request
                                .contentType(ContentType.JSON)
                                .body(employee))
        );
        try {

            actor.remember("statusCode", SerenityRest.lastResponse().getStatusCode());
            actor.remember("statusMessage", SerenityRest.lastResponse().jsonPath().getString("status"));
            actor.remember("message", SerenityRest.lastResponse().jsonPath().getString("message"));

            Employee employee = SerenityRest.lastResponse().jsonPath().getObject("data", Employee.class);
            actor.remember("id", employee.getId());
        } catch (JsonPathException fapi) {
            throw new FailedOfApi("Failed when trying to create employee with name " + employee.getName() + " and the code is: " + SerenityRest.lastResponse().getStatusCode(), fapi);
        }
    }
}
