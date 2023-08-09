package ru.inno.test;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class ConfigTest {

    @Test
    @Tag("runMe")
    public void systemPropertiesTest(){
        String mongoUrl = System.getProperty("mongoUrl");
        String mongoDbName = System.getProperty("mongoDbName");
        String mongoCollection = System.getProperty("mongoCollection");

        System.out.println(mongoUrl);
        System.out.println(mongoDbName);
        System.out.println(mongoCollection);
    }
}
