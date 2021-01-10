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

public class BackupsManager extends DatabaseToolManager {
	private String folderPath = "Sauvegardes/";

	public BackupsManager(SessionFactoryHandler sessionFactoryHandler, String folderPath) {
		super(sessionFactoryHandler);

		this.folderPath = folderPath;
	}

	public void createBackup(Path path) {
		try {
			String[] command = new String[] { this.getMysqlFolder() + "/bin/" + "mysqldump ",
					"-u" + this.getDatabaseUsername(), "-p" + this.getDatabasePassword(), this.getDatabaseName() };

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
			String[] command = new String[] { this.getMysqlFolder() + "/bin/" + "mysql ",
					"-u" + this.getDatabaseUsername(), "-p" + this.getDatabasePassword(), this.getDatabaseName() };

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

	public String getFolderPath() {
		return folderPath;
	}

	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}
}
