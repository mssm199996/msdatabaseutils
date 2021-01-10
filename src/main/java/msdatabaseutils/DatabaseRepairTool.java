package msdatabaseutils;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.Arrays;

public class DatabaseRepairTool extends DatabaseToolManager {

	public void repair() throws IOException, InterruptedException {
		String[] command = new String[] { this.getMysqlFolder() + "/bin/" + "mysqlcheck ", "--auto-repair", "--use-frm",
				"-u" + this.getDatabaseUsername(), "-p" + this.getDatabasePassword(), this.getDatabaseName() };

		ProcessBuilder processBuilder = new ProcessBuilder(Arrays.asList(command));
		processBuilder.redirectError(Redirect.INHERIT);
		processBuilder.redirectOutput(Redirect.INHERIT);

		Process process = processBuilder.start();
		process.waitFor();
	}
}
