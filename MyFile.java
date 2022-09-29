import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;

public class MyFile implements Serializable {
	private String command;
	private File file;
	private FileOutputStream fileFileOutputStream;

	public MyFile() {

	}

	public MyFile(String command, File file) {
		super();
		this.command = command;
		this.file = file;
		this.fileFileOutputStream = fileFileOutputStream;
	}

	public String getCommand() {
		return command;
	}
	public FileOutputStream getFileOutputStream() {
		return fileFileOutputStream;
	}
	public void setFileOutputStream(FileOutputStream fileFileOutputStream) {
		this.fileFileOutputStream = fileFileOutputStream;
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
