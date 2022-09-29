import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpCookie;
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
			ObjectOutputStream objectOutputStream = null;
			// for receive
			ObjectInputStream objectInputStream = null;
			File file = new File(fileName);
			switch (command) {
			case "put":
				objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
				System.out.println("put");
				MyFile myFile = new MyFile();
				myFile.setCommand(command);
				myFile.setFile(file);
				myFile.setExtension(fileName.substring(fileName.lastIndexOf(".") + 1));
				myFile.setContentFiles(Files.readAllBytes(file.toPath()));
				objectOutputStream.writeObject(myFile);
				System.out.println("Object file has sent ....");
				break;
			case "get":
				objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
				objectInputStream = new ObjectInputStream(socket.getInputStream());
				MyFile f = new MyFile();
				f.setCommand(command);
				f.setFileName(fileName);
				objectOutputStream.writeObject(f);

				System.out.println("get");
				MyFile rFile = (MyFile) objectInputStream.readObject();
				if (rFile.getStatus() != null) {
					System.out.println(rFile.getStatus());
				}else {
					byte[] content = (byte[]) rFile.getContentFiles();
					@SuppressWarnings("resource")
					FileOutputStream fileOutputStream = new FileOutputStream("get_" + System.currentTimeMillis() + "." + rFile.getExtension());
					fileOutputStream.write(content);
					System.out.println("Received file !");
				}
				objectOutputStream.flush();

				break;
				
			case "quit":
				socket.close();
				System.out.println("quit socket");
				break;
			default:
				System.out.println("command not supported");
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
