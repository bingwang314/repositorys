package spring_demo.component;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.List;


/**
 * @DESC
 * @AUTHOR wangbing
 * @DATE 2019-04-24
 */
public class DBTemplate extends JdbcTemplate{

    public DBTemplate() {
    }

    public DBTemplate(DataSource dataSource) {
        super(dataSource);
    }

    public DBTemplate(DataSource dataSource, boolean lazyInit) {
        super(dataSource, lazyInit);
    }

    private <T> T requiredSingleResult(Collection<T> results){
        if (CollectionUtils.isEmpty(results)) return null;
        if (results.size() > 1){
            throw new IncorrectResultSizeDataAccessException(1, results.size());
        }
        return results.iterator().next();
    }

    @Override
    protected <T> RowMapper<T> getSingleColumnRowMapper(Class<T> requiredType) {
        // 判断是否为基本数据类型
        if(BeanUtils.isSimpleValueType(requiredType)){
            return super.getSingleColumnRowMapper(requiredType);
        }
        return new BeanPropertyRowMapper<>(requiredType);
    }

    /**
     * 返回刚刚插入的自增id
     * @return 自增id
     * @throws DataAccessException
     */
    public Long getLastInsertId() throws DataAccessException{
        List<Long> results = query("select LAST_INSERT_ID()", super.getSingleColumnRowMapper(Long.class));
        return DataAccessUtils.requiredSingleResult(results);
    }

    @Override
    public <T> T queryForObject(String sql, RowMapper<T> rowMapper) throws DataAccessException {
        List<T> results = query(sql, rowMapper);
        return requiredSingleResult(results);
    }

    @Override
    public <T> T queryForObject(String sql, Object[] args, int[] argTypes, RowMapper<T> rowMapper) throws DataAccessException {
        List<T> results = query(sql, args, argTypes, new RowMapperResultSetExtractor<T>(rowMapper, 1));
        return requiredSingleResult(results);
    }

    @Override
    public <T> T queryForObject(String sql, Object[] args, RowMapper<T> rowMapper) throws DataAccessException {
        List<T> results = query(sql, args, new RowMapperResultSetExtractor<T>(rowMapper, 1));
        return requiredSingleResult(results);
    }

    @Override
    public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... args) throws DataAccessException {
        List<T> results = query(sql, args, new RowMapperResultSetExtractor<T>(rowMapper, 1));
        return requiredSingleResult(results);
    }
}