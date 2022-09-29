import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;

public class MyFile implements Serializable {
	private static final long serialVersionUID = 1L;
	private String command;
	private File file;
	private String fileName;
	private String extension;
	private String status;
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	private byte[] contentFiles;
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getContentFiles() {
		return contentFiles;
	}

	public void setContentFiles(byte[] contentFiles) {
		this.contentFiles = contentFiles;
	}

	public MyFile() {

	}

	public MyFile(String command, File file, byte[] contentFiles) {
		super();
		this.command = command;
		this.file = file;
		this.contentFiles = contentFiles;

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
