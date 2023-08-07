package ru.inno.test;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.inno.model.Cat;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoTypedTest {
    public static final String CONNECTION_STRING = "mongodb://";

    private MongoClient client;
    private MongoCollection<Cat> collection;

    @BeforeEach
    public void setUp() {
        CodecRegistry codecRegistry = fromRegistries(
                getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().register(Cat.class).automatic(true).build())
        );

        MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(codecRegistry)
                .applyConnectionString(new ConnectionString(CONNECTION_STRING))
                .build();


        client = MongoClients.create(settings);
        collection = client.getDatabase("b9kdrqpkhmq9utpwymt5").getCollection("cats", Cat.class);
    }

    @AfterEach
    public void tearDown() {
        if (client != null) {
            client.close();
        }
    }

    @Test
    public void getCats(){
        FindIterable<Cat> catsList = collection.find().filter(Filters.gte("age", 2));
        for (Cat cat : catsList) {
            System.out.println(cat);
        }
    }

    @Test
    public void intInteger(){
        Cat intCat = collection.find().filter(Filters.eq("name", "Инт")).first();;

        System.out.println(intCat);
        //Cat{id=64d14949ed4951faf98c9cdb, name='Инт',age=null} Integer
        //Cat{id=64d14949ed4951faf98c9cdb, name='Инт',age=0} int
    }
}
