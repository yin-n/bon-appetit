import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class ImportJsonToMongoDB {
    public static void main(String[] args) throws IOException {
        // Connect to MongoDB server
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // Select the database and collection
        MongoDatabase database = mongoClient.getDatabase("mydb");
        MongoCollection<Document> collection = database.getCollection("mycollection");

        // Read the JSON file
        File file = new File("path/to/myfile.json");
        byte[] jsonData = Files.readAllBytes(file.toPath());
        String jsonString = new String(jsonData, StandardCharsets.UTF_8);

        // Parse the JSON data and insert it into MongoDB
        List<Document> documents = Document.parse(jsonString);
        collection.insertMany(documents);

        // Close the MongoDB client
        mongoClient.close();
    }
}

