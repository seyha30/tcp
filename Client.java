import java.io.File;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author seyha.sin Sep 29, 2022/2022 : 11:13:51 AM
 */
public class Client {
	public static void main(String[] args) {
		Socket socket = null;
		try {
			socket = new Socket("localhost", 9999);
			File file = new File("aaa.zip");
			MyFile myFile = new MyFile();
			myFile.setCommand("put");
			myFile.setFile(file);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectOutputStream.writeObject(myFile);
			System.out.println("Object file has sent ....");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
