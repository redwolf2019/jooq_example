package com.redwolf.example.jooq.configuration;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JooqConfiguration {


    @Autowired
    private DataSource dataSource;
    @Autowired
    private DataSource shardingDataSource;


    @Bean(name = "dsl")
    public DSLContext getDslContest() {
        org.jooq.Configuration configuration = new DefaultConfiguration()
                .set(dataSource)
                .set(new Settings().withRenderSchema(false)
                        .withExecuteLogging(true)
                        .withRenderFormatted(false))
                .set(SQLDialect.MYSQL);
        return DSL.using(configuration);
    }

    @Bean(name = "sharding")
    public DSLContext getShardingDslContest() {
        org.jooq.Configuration configuration = new DefaultConfiguration()
                .set(shardingDataSource)
                .set(new Settings().withRenderSchema(false)
                        .withExecuteLogging(true)
                        .withRenderFormatted(false))
                .set(SQLDialect.MYSQL);
        return DSL.using(configuration);
    }

}
