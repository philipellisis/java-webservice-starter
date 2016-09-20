package test_webservice.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.jdbc.core.PreparedStatementCreator;

public class BathroomSitePostStatement implements PreparedStatementCreator {
	private BathroomSitePostBody bathroomSitePostBody;
	BathroomSitePostStatement(BathroomSitePostBody bathroomSitePostBody){
		this.bathroomSitePostBody = bathroomSitePostBody;
	}
	
	@Override
	public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		String sql = "insert into toilet.bathroom_site (gender, number_stalls, number_urinals, longitude, latitude) values (?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, bathroomSitePostBody.getGender());
        ps.setInt(2, bathroomSitePostBody.getNumberStalls());
        ps.setInt(3, bathroomSitePostBody.getNumberUrinals());
        ps.setDouble(4, bathroomSitePostBody.getLatitude());
        ps.setDouble(5, bathroomSitePostBody.getLongitude());
        return ps;

	}
}
