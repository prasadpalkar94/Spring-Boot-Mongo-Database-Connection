package config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClientSettings;

@Configuration
@Component
public class MongoConfig {
	
	@Bean
	@Primary
	public void mongoConnection(){
	ConnectionString connString = new ConnectionString(
	    "mongodb+srv://prasad:root1234@localhost:27017/mycustomers?w=majority"
	);
	MongoClientSettings settings = MongoClientSettings.builder()
	    .applyConnectionString(connString)
	    .retryWrites(true)
	    .build();
	MongoClient mongoClient = MongoClients.create(settings);
	MongoDatabase database = mongoClient.getDatabase("mycustomers");
	}
}
