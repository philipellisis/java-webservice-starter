package test_webservice.test;

public class SuccessDataModel {
	private String status;
	private String statusCode;
	private String id;
	
	/**
	 * @param status http status
	 * @param statusCode http status cod
	 * @param id -
	 */
	public SuccessDataModel(String status, String statusCode, String id)
	{
		this.status = status;
		this.statusCode = statusCode;
		this.id = id;
	}
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return this.status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return this.statusCode;
	}
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
}
