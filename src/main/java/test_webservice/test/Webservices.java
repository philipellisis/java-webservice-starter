package test_webservice.test;

import java.sql.Connection;

import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	
	private Connection conn;
	@GET
	@Path("/{materialid}")
	public Response material(@PathParam("materialid") String materialId){

		int rowCount = jdbcTemplate.queryForObject("select count(*) from phil.users", Integer.class);
		
		
		return handleResult(new SuccessDataModel("Success", "200", Integer.toString(rowCount)), Status.OK);
	}
	protected Response handleResult(final Object entity, Response.Status myStatus) {
		ResponseBuilder responseBuilder = Response.status(myStatus);
		responseBuilder.type(MediaType.APPLICATION_JSON);
		responseBuilder.entity(entity);
		return responseBuilder.build();
	}
}
