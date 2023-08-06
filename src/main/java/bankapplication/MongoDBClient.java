package bankapplication;

import com.mongodb.*;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBClient {

    private MongoDatabase database;
    private MongoClient mongoClient;
    private MongoCollection mCollection;

    public void DBconnection() {
        // Replace the placeholder with your Atlas connection string
        String uri = "mongodb+srv://pratyush_motha:hEvalckHFiTx6VvH@cluster0.irga57n.mongodb.net/";

        // Construct a ServerApi instance using the ServerApi.builder() method
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(uri))
                .serverApi(serverApi)
                .build();

        // Create a new client and connect to the server
        try {
            mongoClient = MongoClients.create(settings);
            database = mongoClient.getDatabase("Bank");
            try {
                // Send a ping to confirm a successful connection
                Bson command = new BsonDocument("ping", new BsonInt64(1));
                Document commandResult = database.runCommand(command);
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
            } catch (MongoException me) {
                System.err.println(me);
            }
        } catch (MongoException me) {
            System.err.println(me);
        }
    }

    public void getDocumentsFromCollection() {
        mCollection = database.getCollection("Accounts");
        FindIterable<Document> iterDoc = mCollection.find();
        // Getting the iterator
        for (Document doc : iterDoc) {
            System.out.println(doc.toJson());
        }

    }


    public void insertDocumentsToCollection(String accountName, double accountBalance, String accountType) {
        mCollection = database.getCollection("Accounts");
        Document document = new Document();
        document.put("AccountName", accountName);
        document.put("AccountBalance", accountBalance);
        document.put("AccountType", accountType);
        mCollection.insertOne(document);
    }

    public void closeConnection() {
        mongoClient.close();
    }

}
