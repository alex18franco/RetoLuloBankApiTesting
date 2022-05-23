package co.com.lulobank.apirest.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class Attribute implements Question<Boolean> {

    private String attribute;

    public Attribute(String attribute) {
        this.attribute = attribute;
    }

    public static Attribute hasValue(String attribute){
        return new Attribute(attribute);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return !actor.recall(attribute).toString().isEmpty();
    }
}
