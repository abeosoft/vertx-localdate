package localdatetest.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import localdatetest.LocalDateDeserializer;
import localdatetest.LocalDateSerializer;

import java.time.LocalDate;

@DataObject(generateConverter = true)
public class Person {

    private Integer personId;
    private String name;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dob;

    public Person() {
    }

    public Person(Person person) {
        this.name = person.getName();
        this.dob = person.getDob();
        this.personId = person.getPersonId();
    }

    public Person(JsonObject json) {
        PersonConverter.fromJson(json, this);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();

        PersonConverter.toJson(this, json);

        return json;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }
}
