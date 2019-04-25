package spring_demo.component;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import spring_demo.support.DBTemplate;

/**
 * @DESC
 * @AUTHOR wangbing
 * @DATE 2019-04-24
 */
@Configuration
public class DataSourceConfig {
    @Primary
    @Bean("t1")
    public DBTemplate createT1Template(@Qualifier("t1DataSource")DataSource dataSource){
        return new DBTemplate(dataSource);
    }

    @Bean("t2")
    public DBTemplate createT2Template(@Qualifier("t2DataSource") DataSource dataSource){
        return new DBTemplate(dataSource);
    }

    @Primary
    @Bean("t1DataSource")
    @ConfigurationProperties("spring.datasource.t1")
    public DataSource createT1DataSource(){
        DataSource dataSource = DataSourceBuilder.create().type(HikariDataSource.class).build();
        return dataSource;
    }

    @Bean("t2DataSource")
    @ConfigurationProperties("spring.datasource.t2")
    public DataSource createT2DataSource(){
        DataSource dataSource = DataSourceBuilder.create().type(HikariDataSource.class).build();
        return dataSource;
    }
}
