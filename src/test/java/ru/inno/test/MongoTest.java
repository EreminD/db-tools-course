package ru.inno.test;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Projections;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MongoTest {
    public static final String CONNECTION_STRING = "mongodb://";

    private MongoClient client;
    private MongoCollection<Document> collection;

    @BeforeEach
    public void setUp() {
        client = MongoClients.create(CONNECTION_STRING);
        collection = client.getDatabase("b9kdrqpkhmq9utpwymt5").getCollection("cats");
    }

    @AfterEach
    public void tearDown() {
        if (client != null) {
            client.close();
        }
    }

    @Test
    public void findAll() {
        Bson fields = Projections.fields(
          Projections.include("name", "age", "breed", "color"),
          Projections.excludeId()
        );

        FindIterable<Document> documents = collection.find().projection(fields);
        for (Document document : documents) {
            System.out.println(document.get("color"));
            Integer age = document.get("age", Integer.class);
            System.out.println(age);
        }
    }

    @Test
    public void insert(){
        Document document = new Document().append("name", "Валери").append("age", 1).append("breed", "Сиамская");
        InsertOneResult insertOneResult = collection.insertOne(document);
        System.out.println(insertOneResult.getInsertedId());
    }
}