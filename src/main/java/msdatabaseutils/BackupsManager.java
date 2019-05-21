package msdatabaseutils;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class BackupsManager {
	private String databaseUsername = "root";
	private String databasePassword = "root";
	private String databaseName = "";
	private String folderPath = "Sauvegardes/";

	public BackupsManager(String databaseName) {
		this.databaseName = databaseName;
	}

	public BackupsManager(String databaseUsername, String databasePassword, String databaseName, String folderPath) {
		this(databaseName);

		this.databaseUsername = databaseUsername;
		this.databasePassword = databasePassword;
	}

	public BackupsManager(SessionFactoryHandler sessionFactoryHandler, String folderPath) {
		this(sessionFactoryHandler.getDatabaseUsername(), sessionFactoryHandler.getDatabasePassword(),
				sessionFactoryHandler.getDatabaseName(), folderPath);
	}

	public void createBackup(Path path) {
		try {
			/*String[] command = new String[] { this.getMySQLFolderPath() + "mysqldump ", "-u" + this.databaseUsername,
					"-p" + this.databasePassword, this.databaseName };*/
			
			String[] command = new String[] { "mysqldump ", "-u" + this.databaseUsername,
					"-p" + this.databasePassword, this.databaseName };

			ProcessBuilder processBuilder = new ProcessBuilder(Arrays.asList(command));
			processBuilder.redirectError(Redirect.INHERIT);
			processBuilder.redirectOutput(Redirect.to(path.toFile()));

			Process process = processBuilder.start();
			process.waitFor();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void loadBackup(Path path) {
		try {
			/*String[] command = new String[] { this.getMySQLFolderPath() + "mysql ", "-u" + this.databaseUsername,
					"-p" + this.databasePassword, this.databaseName };*/
			
			String[] command = new String[] { "mysql ", "-u" + this.databaseUsername,
					"-p" + this.databasePassword, this.databaseName };

			ProcessBuilder processBuilder = new ProcessBuilder(Arrays.asList(command));
			processBuilder.redirectError(Redirect.INHERIT);
			processBuilder.redirectInput(Redirect.from(path.toFile()));

			Process process = processBuilder.start();
			process.waitFor();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void initiateBackup() {
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					if (!Files.exists(Paths.get(folderPath)))
						Files.createDirectory(Paths.get(folderPath));

					createBackup(Paths.get(
							folderPath + "/" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "@"
									+ LocalTime.now().format(DateTimeFormatter.ofPattern("hh-mm-ss")) + ".mssmsql"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}));
	}

	/*public String getMySQLFolderPath() {
		String programFilesPath = System.getenv("ProgramFiles");
		String mysqlBinFolder = programFilesPath + "/MySQL/MySQL Server 5.7/bin/";

		while (mysqlBinFolder.contains("\\"))
			mysqlBinFolder = mysqlBinFolder.replace('\\', '/');

		return mysqlBinFolder;
	}*/

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

	public String getFolderPath() {
		return folderPath;
	}

	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}
}
