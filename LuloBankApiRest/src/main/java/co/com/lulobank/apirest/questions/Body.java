package co.com.lulobank.apirest.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class Body implements Question<String> {
    public static Body is(){
        return new Body();
    }

    @Override
    public String answeredBy(Actor actor) {
        return actor.recall("bodyEmployee").toString();
    }
}
