package ru.inno.test;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.inno.ext.props.PropertyProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigTest {

    @Test
    public void systemPropertiesTest() {
        String mongoUrl = System.getProperty("mongoUrl");
        String mongoDbName = System.getProperty("mongoDbName");
        String mongoCollection = System.getProperty("mongoCollection");

        System.out.println(mongoUrl);
        System.out.println(mongoDbName);
        System.out.println(mongoCollection);
    }

    @Test
    @Tag("runMe")
    public void propertiesFile() {
        Properties properties = PropertyProvider.getInstance().getProps();

        String mongoUrl = properties.getProperty("mongo.url");
        String mongoDbName = properties.getProperty("mongo.db");
        String mongoCollection = properties.getProperty("mongo.collection");
        String nullValueKey = properties.getProperty("not exist", "def val");

        System.out.println(mongoUrl);
        System.out.println(mongoDbName);
        System.out.println(mongoCollection);
        System.out.println(nullValueKey);
    }
}
