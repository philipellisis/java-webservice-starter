package test_webservice.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;


/**
 * Hello world!
 *
 */
@SuppressWarnings("nls")
@Component("materialManagementService")
@Consumes({ "application/json"})
@Produces({ "application/json"})
@Path("/phil")
public class Webservices 
{
	private static final Logger log = LoggerFactory.getLogger(Webservices.class);
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    public void setDataSource(DataSource dataSource) throws SQLException {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        conn = dataSource.getConnection();
    }
	
	
	private Connection conn;
	@GET
	@Path("/{bathroomSiteId}")
	public Response getBathroomSite(@PathParam("bathroomSiteId") int bathroomSiteId) throws Exception{
		Object bathroomSitePostBody = jdbcTemplate.queryForObject("select * from toilet.bathroom_site where bathroom_site.id = ?", new GenericRowMapper(BathroomSite.class.getConstructor()), bathroomSiteId);
		return handleResult(bathroomSitePostBody, Status.OK);
	}
	
	@GET
	@Path("/")
	public Response getBathroomSites() throws Exception{
		List<Object> bathroomSitePostBody = jdbcTemplate.query("select * from toilet.bathroom_site", new GenericRowMapper(BathroomSite.class.getConstructor()));
		return handleResult(bathroomSitePostBody, Status.OK);
	}
	
	@POST
	@Path("/")
	public Response materialpost(BathroomSitePostBody body){
		
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new GenericPostGenerator(body,"insert into toilet.bathroom_site (gender, number_stalls, number_urinals, longitude, latitude) values (?,?,?,?,?)"), holder);
		return handleResult(new SuccessDataModel("Success", "200", holder.getKeys().get("id").toString()), Status.OK);
	}
	protected Response handleResult(final Object entity, Response.Status myStatus) {
		ResponseBuilder responseBuilder = Response.status(myStatus);
		responseBuilder.type(MediaType.APPLICATION_JSON);
		responseBuilder.entity(entity);
		return responseBuilder.build();
	}
	
	
}
