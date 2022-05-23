package co.com.lulobank.apirest.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class Messaje implements Question<String> {

    public static Messaje ofResource(){
        return new Messaje();
    }

    @Override
    public String answeredBy(Actor actor) {
        return actor.recall("message").toString();
    }
}
