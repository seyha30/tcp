import java.io.*;
import java.net.*;

import javax.swing.text.GapContent;

public class TCPFileServer {
	public static void main(String[] args) {
		ServerSocket server = null;
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			server = new ServerSocket(9999);

			while (true) {
				System.out.println("-------------------------");
				System.out.println("Wait for client to connect....");
				socket = server.accept();
				System.out.println("Got connection from " + socket.getInetAddress());
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);

 		if (in.readLine().equals("put")) {
 			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
 			
//					System.out.println("put");
//					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//					String filename = bufferedReader.readLine();
//					File file = new File(filename);
//					long filesize = file.length();
//					FileOutputStream outFile = new FileOutputStream(
//							"put" + System.currentTimeMillis() + "_" + filename);
//					try {
//						InputStream inSocket = socket.getInputStream();
//						int b;
//						long l = 0;
//						byte[] buf = new byte[1024];
//						while ((b = inSocket.read(buf, 0, 1024)) != -1) {
//							l += b;
//							outFile.write(buf, 0, b);
//							if (l == filesize)
//								break;
//						}
//						System.out.println("Receiving from client completed!");
//					} catch (SocketTimeoutException ste) {
//						System.out.println("[Error] Receiving timeout!!!!");
//					} finally {
//						outFile.close();
//					}
			}
				String filename = in.readLine();
				File file = new File(filename);
				long filesize = file.length();

				if (!file.exists()) {
					System.out.println("File does not exists on server");
					out.println("-1"); // File not exist
					continue;
				} else {
					System.out.println("File size = " + filesize + " bytes");
					out.println("" + filesize);
				}

				if (in.readLine().equals("OK")) {
					System.out.println("Sending " + filename + " ...");
					OutputStream outSocket = socket.getOutputStream();
					FileInputStream inFile = new FileInputStream(filename);
					byte[] buf = new byte[1024];
					int b;
					long l = 0;
					while ((b = inFile.read(buf, 0, 1024)) != -1) {
						l += b;
						outSocket.write(buf, 0, b);
					}
					inFile.close();
					System.out.println("Sending completed!");
				}

			}

		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}
}
