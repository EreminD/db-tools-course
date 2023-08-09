package ru.inno.ext.props;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties"
})
public interface AppConfig extends Config {

    int year();

    String name();

    boolean ok();

    int[] numbers();

    @Key("test.url")
    String testUrl();

    @Key("test.user")
    String testUser();

    @Key("test.pass")
    String testPass();
}
