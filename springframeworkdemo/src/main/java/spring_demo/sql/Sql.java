package spring_demo.sql;

public class Sql {
    public static final String SELECT_SQL = "select id, name, score from student where id > ? and score > ?";
    public static final String INSERT_SQL = "insert into student (id, name, score) values(?, ?, ?) on duplicate key update id = ?";
}
