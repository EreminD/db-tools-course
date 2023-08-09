package ru.inno.test;

import org.aeonbits.owner.ConfigCache;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.inno.ext.props.AppConfig;
import ru.inno.ext.props.PropertyProvider;

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

    @Test
    @Tag("runMe")
    public void ownerProps(){
        AppConfig config = ConfigCache.getOrCreate(AppConfig.class);

        System.out.println(config.year());
        System.out.println(config.name());
        System.out.println(config.ok());
        System.out.println(config.numbers()[2]);
        System.out.println(config.testUrl());
    }
}
