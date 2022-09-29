import java.io.File;
import java.io.Serializable;

public class MyFile implements Serializable {
	private String command;
	private File file;

	public MyFile() {

	}

	public MyFile(String command, File file) {
		super();
		this.command = command;
		this.file = file;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
