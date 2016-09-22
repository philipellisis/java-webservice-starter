package test_webservice.test;

import java.sql.Connection;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.jdbc.core.PreparedStatementCreator;

public class GenericPostGenerator implements PreparedStatementCreator {
	private Object body;
	private String sql;
	GenericPostGenerator(Object body, String sql){
		this.body = body;
		this.sql = sql;
	}
	
	@Override
	public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        Field[] fields =  body.getClass().getDeclaredFields();
        fields = AnnotationSorter.sort(fields);
        int i = 1;
        for (Field field : fields) {
        	field.setAccessible(true);
        	try{
			if(field.getType().equals(int.class)){
				ps.setInt(i, field.getInt(body));
			} else if(field.getType().equals(String.class)){
				ps.setString(i, (String)field.get(body));
			} else if(field.getType().equals(double.class)){
				ps.setDouble(i, field.getDouble(body));
			}
        	}
        	catch (IllegalAccessException e){
        		e.printStackTrace();
        	}
			i++;
        }
        return ps;

	}
}
