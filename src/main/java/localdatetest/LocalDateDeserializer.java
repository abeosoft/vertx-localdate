package localdatetest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

    protected LocalDateDeserializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        String text = jp.readValueAs(String.class);
        if (text == null) {
            return LocalDate.MAX;
        } else {
            return LocalDate.parse(text, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
    }
}
