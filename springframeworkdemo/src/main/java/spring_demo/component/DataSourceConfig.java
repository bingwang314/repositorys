package spring_demo.component;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @DESC
 * @AUTHOR wangbing
 * @DATE 2019-04-24
 */
@Configuration
public class DataSourceConfig {
    public JdbcTemplate createT1Template(@Qualifier("t1DataSource")DataSource dataSource){
        return new DBTemplate(dataSource);
    }
}
