package database;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.entities.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Converter {     //author and book extends from one class?
    private final Logger log= LoggerFactory.getLogger(DatabaseHelper.class);
    private String str;

    public String authorToJSON(Author author) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        str = mapper.writeValueAsString(author);
        log.info("json created");
        return str;
    }

    public Author toAuthorObject() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        log.info("json was converted to object");
        return mapper.readValue(str, Author.class);
    }
}
