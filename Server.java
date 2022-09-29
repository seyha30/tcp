import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;

/*
 * @author seyha.sin
 * Sep 29, 2022/2022 : 10:59:28 AM
 */
public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(9999);
			Socket socket = null;
			MyFile myFile = null;
			while (true) {
				System.out.println("Wait for client ....");
				socket = serverSocket.accept();
				System.out.println("Accepted from " + socket.getInetAddress());
//				send object out put stream
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
//				receive object 
				ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
				try {
					myFile = (MyFile) objectInputStream.readObject();
					if (myFile.getCommand().equalsIgnoreCase("put")) {
						byte[] content = (byte[]) myFile.getContentFiles();
						Files.write(myFile.getFile().toPath(), content);
						System.out.println("todo read file" + myFile.getFile().getPath());
					} else if (myFile.getCommand().equalsIgnoreCase("get")) {
						String fileName = myFile.getFileName();
						System.out.println("Please read file name and send === > " + fileName);

					} else {
						System.out.println("todo send file");
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
