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
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MongoTest {
    private static final String CONNECTION_STRING = System.getProperty("mongoUrl");
    private static final String DB_NAME = System.getProperty("mongoDbName", "b9kdrqpkhmq9utpwymt5");
    private static final String COLLECTION_NAME = System.getProperty("mongoCollection", "cats");
    private MongoClient client;
    private MongoCollection<Document> collection;

    @BeforeEach
    public void setUp() {
        client = MongoClients.create(CONNECTION_STRING);
        collection = client.getDatabase(DB_NAME).getCollection(COLLECTION_NAME);
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
        List<Document>  cats = new ArrayList<>();
        for (Document document : documents) {
            cats.add(document);
//            System.out.println(document.get("color"));
//            Integer age = document.get("age", Integer.class);
//            System.out.println(age);
        }
        assertEquals(11, cats.size());
    }

    @Test
    public void insert(){
        Document document = new Document().append("name", "Валери").append("age", 1).append("breed", "Сиамская");
        InsertOneResult insertOneResult = collection.insertOne(document);
        System.out.println(insertOneResult.getInsertedId());
    }
}