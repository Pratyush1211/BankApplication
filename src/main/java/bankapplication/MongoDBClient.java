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
        String uri = "";

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
                System.out.println("You successfully connected to MongoDB!");
            } catch (MongoException me) {
                System.err.println(me);
            }
        } catch (MongoException me) {
            System.err.println(me);
        }
    }


    // getter for collection
    public void getDocumentsFromCollection() {
        mCollection = database.getCollection("Accounts");
        FindIterable<Document> iterDoc = mCollection.find();
        for (Document doc : iterDoc) {
            System.out.println("Account Holder is: " + doc.get("AccountName") + ", has a Balance of: " + doc.get("AccountBalance") +", Account type is: "+ doc.get("AccountType"));
        }

    }



    public void insertDocumentsToCollection(BankAccount ba) {
        mCollection = database.getCollection("Accounts");
        Document document = new Document();
        document.put("AccountName", ba.getAccountName());
        document.put("AccountBalance", ba.getAccountBalance());
        document.put("AccountType", ba.getAccountType());
        mCollection.insertOne(document);
    }

    public void deleteDocumentFromCollection(String AccountName){
        mCollection = database.getCollection("Accounts");
        mCollection.deleteOne(new Document("AccountName", AccountName));
        System.out.print("Account Deleted Successfully!");
    }

    public void closeConnection() {
        mongoClient.close();
    }

}
