package test_webservice.test;


import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class GenericRowMapper implements RowMapper<Object> {
	private Object object;
	public GenericRowMapper(Object object){
		this.object = object;
	}
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		//BathroomSitePostBody bathroomSitePostBody = new BathroomSitePostBody();
		for (Field field : object.getClass().getDeclaredFields()) {
			if(field.getType().equals(int.class)){
				set(object,field.getName(),rs.getInt(camelToSnake(field.getName())));
			} else if(field.getType().equals(String.class)){
				set(object,field.getName(),rs.getString(camelToSnake(field.getName())));
			} else if(field.getType().equals(double.class)){
				set(object,field.getName(),rs.getDouble(camelToSnake(field.getName())));
			}
			
			//bathroomSitePostBody.setId(rs.getInt("id"));
			//bathroomSitePostBody.setGender(rs.getString("gender"));
			//bathroomSitePostBody.setNumberStalls(rs.getInt("number_stalls"));
			//bathroomSitePostBody.setNumberUrinals(rs.getInt("number_urinals"));
			//bathroomSitePostBody.setLongitude(rs.getDouble("longitude"));
			//bathroomSitePostBody.setLatitude(rs.getDouble("latitude"));
		}

		return object;

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
