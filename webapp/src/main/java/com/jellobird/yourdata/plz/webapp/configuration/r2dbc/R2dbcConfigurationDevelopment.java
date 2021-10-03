package com.jellobird.yourdata.plz.webapp.configuration.r2dbc;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
@Profile("development")
class R2dbcConfigurationDevelopment {

    @Bean
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(
                ConnectionFactoryOptions.builder()
                        .option(DRIVER, "postgresql")
                        .option(HOST, "localhost")
                        .option(USER, "plz")
                        .option(PASSWORD, "plz")
                        .option(DATABASE, "plz")
                        .build());
    }

}