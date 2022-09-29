import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;

/**
 * @author seyha.sin Sep 29, 2022/2022 : 11:13:51 AM
 */
public class Client {
	private static boolean checkArgs(String[] args) {
		if (args.length <= 0) {
			System.out.println("Invalid command please types");
			System.out.println("get filename");
			System.out.println("put filename");
			System.out.println("quit");
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		Socket socket = null;

		if (!checkArgs(args)) {
			return;
		}
		String command = args[0].toLowerCase();
		String fileName = args[1].toLowerCase();
		try {
			socket = new Socket("localhost", 9999);
			// for send
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			// for receive
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
			File file = new File(fileName);
			switch (command) {
			case "put":
				System.out.println("put");
				MyFile myFile = new MyFile();
				myFile.setCommand(command);
				myFile.setFile(file);
				myFile.setContentFiles(Files.readAllBytes(file.toPath()));
				objectOutputStream.writeObject(myFile);
				System.out.println("Object file has sent ....");
				break;
			case "get":
				MyFile f = new MyFile();
				f.setCommand(command);
				f.setFileName(fileName);
				objectOutputStream.writeObject(f);
				System.out.println("get");
				// check status
				// write file
				// finish 
				
				break;

			default:
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
