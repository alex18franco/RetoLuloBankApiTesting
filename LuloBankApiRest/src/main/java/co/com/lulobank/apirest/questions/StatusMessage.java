package co.com.lulobank.apirest.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class StatusMessage implements Question<String> {

    public static StatusMessage ofResource(){
        return new StatusMessage();
    }

    @Override
    public String answeredBy(Actor actor) {
        return actor.recall("statusMessage").toString();
    }
}
