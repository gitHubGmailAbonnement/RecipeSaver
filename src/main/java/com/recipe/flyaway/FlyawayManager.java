package com.recipe.flyaway;

import jakarta.annotation.PostConstruct;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FlyawayManager {

    private final String dataSource;
    private final String user;
    private final String mdp; //TODO env. variable

    public FlyawayManager(@Value("${spring.datasource.url}") String dataSource, @Value("${spring.datasource.username}")String user, @Value("${spring.datasource.password}")String mdp) {
        this.dataSource = dataSource;
        this.user = user;
        this.mdp = mdp;
    }

    @PostConstruct
    private void migrateData()
    {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource, user, mdp)
                .load();

// Start the migration
        flyway.migrate();
    }
}
