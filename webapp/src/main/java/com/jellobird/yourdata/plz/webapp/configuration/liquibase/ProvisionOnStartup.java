package com.jellobird.yourdata.plz.webapp.configuration.liquibase;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class ProvisionOnStartup {

    @PostConstruct
    private void init() throws LiquibaseException, SQLException {
        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(dataSource.getConnection()));
        Liquibase liquibase = new liquibase.Liquibase("db/changelog/db.changelog-master.xml", new ClassLoaderResourceAccessor(), database);
        liquibase.update(new Contexts(), new LabelExpression());
    }

    //@Autowired
    Connection connection;

    @Autowired
    DataSource dataSource;

    @Bean
    @Profile("development")
    public DataSource dataSourceDev() {
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.driverClassName("org.postgresql.Driver");
        builder.url("jdbc:postgresql:plz");
        builder.username("plz");
        builder.password("plz");
        return builder.build();
    }

    @Bean
    @Profile("production")
    public DataSource dataSourceProd() {
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.driverClassName("org.postgresql.Driver");
        builder.url("jdbc:postgresql://prod-db/plz");
        builder.username("plz");
        builder.password("plz");
        return builder.build();
    }

}
