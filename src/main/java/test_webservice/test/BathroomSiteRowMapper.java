package test_webservice.test;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BathroomSiteRowMapper implements RowMapper<BathroomSitePostBody> {

	@Override
	public BathroomSitePostBody mapRow(ResultSet rs, int rowNum) throws SQLException {
		BathroomSitePostBody bathroomSitePostBody = new BathroomSitePostBody();
		bathroomSitePostBody.setId(rs.getInt("id"));
		bathroomSitePostBody.setGender(rs.getString("gender"));
		bathroomSitePostBody.setNumberStalls(rs.getInt("number_stalls"));
		bathroomSitePostBody.setNumberUrinals(rs.getInt("number_urinals"));
		bathroomSitePostBody.setLongitude(rs.getDouble("longitude"));
		bathroomSitePostBody.setLatitude(rs.getDouble("latitude"));
		
		return bathroomSitePostBody;
		
	}
	
}
