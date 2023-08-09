package ru.inno.ext.props;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties"
})
public interface DatabaseConfig extends Config {
    // url
    // user
    // pass
}
