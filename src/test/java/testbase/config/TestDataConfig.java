package testbase.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/testdata.properties"
})
public interface TestDataConfig extends Config {
    @Key("api.url")
    String apiUrl();
}
