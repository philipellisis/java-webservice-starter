package test_webservice.test;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class GenericRowMapper implements RowMapper<Object> {
	private Constructor<?> constructor;
	public GenericRowMapper(Constructor<?> constructor){
		this.constructor = constructor;
	}
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Object newObject = null;
		try {
			newObject = constructor.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Field field : newObject.getClass().getDeclaredFields()) {
			if(field.getType().equals(int.class)){
				set(newObject,field.getName(),rs.getInt(camelToSnake(field.getName())));
			} else if(field.getType().equals(String.class)){
				set(newObject,field.getName(),rs.getString(camelToSnake(field.getName())));
			} else if(field.getType().equals(double.class)){
				set(newObject,field.getName(),rs.getDouble(camelToSnake(field.getName())));
			}
		}

		return newObject;

	}
	
    public static String camelToSnake(String camelCaseString)
    {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        return(camelCaseString
                           .replaceAll(regex, replacement)
                           .toLowerCase());
    }
    public static boolean set(Object object, String fieldName, Object fieldValue) {
        Class<?> clazz = object.getClass();
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(object, fieldValue);
                return true;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return false;
    }
}
