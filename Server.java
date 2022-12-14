import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpCookie;
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
//				receive object input stream
				ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
				try {
					myFile = (MyFile) objectInputStream.readObject();
					if (myFile.getCommand().equalsIgnoreCase("put")) {
						byte[] content = (byte[]) myFile.getContentFiles();
						FileOutputStream fileOutputStream = new FileOutputStream(
								"put" + System.currentTimeMillis() + "." + myFile.getExtension());
						fileOutputStream.write(content);
						System.out.println("put file");
					} else if (myFile.getCommand().equalsIgnoreCase("get")) {
						String fileName = myFile.getFileName();
						System.out.println("file name" + fileName);
						File file = new File(fileName);
						if (!file.exists()) {
							System.out.println("File does not exists on server");
							MyFile nFile = new MyFile();
							nFile.setStatus("File does not exists on server");
							objectOutputStream.writeObject(nFile);
							objectOutputStream.flush();
							continue;
						}else {
						myFile = new MyFile();
						myFile.setFile(file);
						myFile.setStatus("ok");
						myFile.setExtension(fileName.substring(fileName.lastIndexOf(".") + 1));
						myFile.setContentFiles(Files.readAllBytes(file.toPath()));
						objectOutputStream.writeObject(myFile);
						objectOutputStream.flush();
						System.out.println("Please read file name and send back client === > " + fileName);
						}

					} else {
						System.out.println("quit");
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
