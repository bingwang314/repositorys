package spring_demo.measure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import spring_demo.bean.Student;
import spring_demo.support.DBTemplate;
import spring_demo.sql.Sql;

import java.util.ArrayList;
import java.util.List;

/**
 * @DESC
 * @AUTHOR wangbing
 * @DATE 2019-04-24
 */

@Service
public class Task {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("t1")
    private DBTemplate t1Db;


    @Autowired
    @Qualifier("t2")
    private DBTemplate t2Db;

    public void calc(){
        Object[] args = {3, 90};

        List<Student> list = t1Db.queryForList(Sql.SELECT_SQL, Student.class, args);
        log.debug("sql参数：{}", args);
        if (list != null && list.size() > 0){
            List<Object[]> batchArgs = getInsertArgs(list);
            t2Db.batchUpdate(Sql.INSERT_SQL, batchArgs);
            log.debug("sql参数：{}", batchArgs);
        }
    }

    private List<Object[]> getInsertArgs(List<Student> students){
        List<Object[]> list = new ArrayList<>();
        for(Student student : students){
            Object[] os = {student.getId(), student.getName(), student.getScore(), student.getId()};
            list.add(os);
        }
        return list;
    }
}
