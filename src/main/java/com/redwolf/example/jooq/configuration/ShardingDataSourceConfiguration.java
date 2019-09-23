package com.redwolf.example.jooq.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class ShardingDataSourceConfiguration {


    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Bean(name = "shardingDataSource")
    public DataSource shardingDataSource() throws SQLException {
        ShardingRuleConfiguration cfg = new ShardingRuleConfiguration();
        cfg.getTableRuleConfigs().add(getDeviceTableCfg());

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(dataSourceProperties.getDriverClassName());
        hikariConfig.setJdbcUrl(dataSourceProperties.getUrl());
        hikariConfig.setUsername(dataSourceProperties.getUsername());
        hikariConfig.setPassword(dataSourceProperties.getPassword());
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        Map<String, DataSource> result = new HashMap<>();
        result.put("ds", dataSource);

        return ShardingDataSourceFactory.createDataSource(result, cfg, new Properties());
    }

    private static KeyGeneratorConfiguration getKeyGeneratorConfiguration() {
        return new KeyGeneratorConfiguration("SNOWFLAKE", "id");
    }

    private TableRuleConfiguration getDeviceTableCfg() {
        TableRuleConfiguration result = new TableRuleConfiguration("device", "ds.device_${0..1}");
        result.setKeyGeneratorConfig(getKeyGeneratorConfiguration());
        return result;
    }


}
