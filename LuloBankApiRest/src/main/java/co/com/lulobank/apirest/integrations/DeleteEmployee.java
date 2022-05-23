package co.com.lulobank.apirest.integrations;

import co.com.lulobank.apirest.util.Resources;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Delete;

public class DeleteEmployee implements Task {

    private String id;

    public DeleteEmployee(String id) {
        this.id = id;
    }

    public static DeleteEmployee byId(String id) {
        return Tasks.instrumented(DeleteEmployee.class,id);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from(Resources.RESOURCE_DELETE+id)
        );
        actor.remember("statusCode", SerenityRest.lastResponse().getStatusCode());
        actor.remember("statusMessage",SerenityRest.lastResponse().jsonPath().getString("status"));
        actor.remember("message",SerenityRest.lastResponse().jsonPath().getString("message"));
    }
}
