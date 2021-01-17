package com.prawda.demoBlogBE;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import java.net.URI;
import static io.r2dbc.pool.PoolingConnectionFactoryProvider.MAX_SIZE;
import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
@EnableR2dbcRepositories
public class DBConfig extends AbstractR2dbcConfiguration {

    @SneakyThrows
    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        String dbUrl = System.getenv("DATABASE_URL");
        URI uri = new URI(dbUrl);
        String user = uri.getUserInfo().split(":")[0];
        String password = uri.getUserInfo().split(":")[1];
        String databaseName = uri.getPath().substring(1);

        return ConnectionFactories.get(ConnectionFactoryOptions.builder()
                .option(Option.valueOf("database"), databaseName)
                .option(Option.valueOf("password"), password)
                .option(Option.valueOf("user"), user)
                .option(PROTOCOL, "postgresql")
                .option(DRIVER, "pool")
                .option(HOST, uri.getHost())
                .option(MAX_SIZE, 15)
                .build());
    }
}