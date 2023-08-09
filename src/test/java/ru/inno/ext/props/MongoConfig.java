package ru.inno.ext.props;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties"
})
public interface MongoConfig extends Config {

    @Key("mongo.url")
    String url();

    @Key("mongo.db")
    String dbName();

    @Key("mongo.collection")
    String collection();
}
