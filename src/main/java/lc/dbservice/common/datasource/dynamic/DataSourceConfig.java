package lc.dbservice.common.datasource.dynamic;

import lc.dbservice.common.datasource.dynamic.entity.MariadbEntity;
import lc.dbservice.common.datasource.dynamic.entity.OracleEntity;
import lc.dbservice.common.datasource.dynamic.entity.SqliteEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

/**
 * @author cocos
 */
@Configuration
public class DataSourceConfig {
    private final MariadbEntity mariadb;
    private  final OracleEntity oracle;
    private final SqliteEntity sqlite;
    DataSourceConfig(MariadbEntity mariadb,OracleEntity oracle,SqliteEntity sqlite){
        this.mariadb=mariadb;
        this.oracle=oracle;
        this.sqlite=sqlite;
    }
    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamic = new DynamicDataSource();
        dynamic.initDataSource(sqliteDataSource(), mariadbDataSource(), oracleDataSource());
        return dynamic;
    }
    private  DataSource mariadbDataSource() {
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setUrl(mariadb.getJdbcUrl());
        source.setDriverClassName(mariadb.getDriverClassName());
        source.setPassword(mariadb.getPassword());
        source.setUsername(mariadb.getUsername());
        return source;
    }
    private  DataSource oracleDataSource() {
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setUrl(oracle.getJdbcUrl());
        source.setDriverClassName(oracle.getDriverClassName());
        source.setPassword(oracle.getPassword());
        source.setUsername(oracle.getUsername());
        return source;
    }
    private  DataSource sqliteDataSource() {
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setUrl(sqlite.getJdbcUrl());
        source.setDriverClassName(sqlite.getDriverClassName());
        return source;
    }
}