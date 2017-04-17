package hello;

//jdbc
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JdbcDAO {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    
    public JdbcDAO () {

    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
    this.jdbcTemplate = jdbcTemplate;  
    }  
      
    public void setupDB() {
        jdbcTemplate.execute("DROP TABLE greetings IF EXISTS");
        // jdbcTemplate.execute("CREATE TABLE greetings(" +
        //         "id SERIAL, content VARCHAR(255))");

        // Split up the array of whole names into an array of first/last names
        // List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
        //         .map(name -> name.split(" "))
        //         .collect(Collectors.toList());

        // List<Object[]> splitUpNames = Arrays.asList({"John Woo"}, {"Jeff Dean"}, {"Josh Bloch"}, {"Josh Long"});

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        // jdbcTemplate.batchUpdate("INSERT INTO greetings(content) VALUES (?)", splitUpNames);
    }

    public int saveGreeting(Greeting e){  
        String query="insert into greetings values('"+e.getId()+"','"+e.getContent()+"')";  
        return jdbcTemplate.update(query);  
    }  
    public int updateGreeting(Greeting e){  
        String query="update greetings set greeting='"+e.getContent()+"' where id='"+e.getId()+"' ";  
        return jdbcTemplate.update(query);  
    }  
    public int deleteGreeting(Greeting e){  
        String query="delete from greetings where id='"+e.getId()+"' ";  
        return jdbcTemplate.update(query);  
    }  

}
