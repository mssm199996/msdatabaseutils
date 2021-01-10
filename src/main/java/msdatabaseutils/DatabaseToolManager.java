package msdatabaseutils;

public class DatabaseToolManager {

	private String databaseUsername = "root";
	private String databasePassword = "mssm1996";
	private String databaseName = "";
	private String mysqlFolder = "C:/Program Files/MySQL/MySQL Server 5.7/";

	public DatabaseToolManager() {
		super();
	}

	public DatabaseToolManager(String databaseName) {
		this.databaseName = databaseName;
	}

	public DatabaseToolManager(String databaseUsername, String databasePassword, String databaseName) {
		this(databaseName);

		this.databaseUsername = databaseUsername;
		this.databasePassword = databasePassword;
	}

	public DatabaseToolManager(SessionFactoryHandler sessionFactoryHandler) {
		this(sessionFactoryHandler.getDatabaseUsername(), sessionFactoryHandler.getDatabasePassword(),
				sessionFactoryHandler.getDatabaseName());
	}

	public String getDatabaseUsername() {
		return databaseUsername;
	}

	public void setDatabaseUsername(String databaseUsername) {
		this.databaseUsername = databaseUsername;
	}

	public String getDatabasePassword() {
		return databasePassword;
	}

	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getMysqlFolder() {
		return mysqlFolder;
	}

	public void setMysqlFolder(String mysqlFolder) {
		this.mysqlFolder = mysqlFolder;
	}
}
