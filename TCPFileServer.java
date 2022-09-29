import java.io.*;
import java.net.*;

import javax.swing.text.GapContent;

public class TCPFileServer {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		BufferedReader bufferedReader = null;
		PrintWriter printWriter = null;
		try {
			serverSocket = new ServerSocket(9999);

			while (true) {
				System.out.println("-------------------------");
				System.out.println("Wait for client to connect....");
				socket = serverSocket.accept();
				System.out.println("Got connection from " + socket.getInetAddress());

				bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				printWriter = new PrintWriter(socket.getOutputStream(), true);
				String filename = bufferedReader.readLine();
				File file = new File(filename);
				long filesize = file.length();

				if (!file.exists()) {
					System.out.println("File does not exists on server");
					printWriter.println("-1"); // File not exist
					continue;
				} else {
					System.out.println("File size = " + filesize + " bytes");
					printWriter.println("" + filesize);
				}

				if (bufferedReader.readLine().equals("OK")) {
					System.out.println("Sending " + filename + " ...");
					OutputStream outputStream = socket.getOutputStream();
					FileInputStream inFile = new FileInputStream(filename);
					byte[] buf = new byte[1024];
					int b;
					long l = 0;
					while ((b = inFile.read(buf, 0, 1024)) != -1) {
						l += b;
						outputStream.write(buf, 0, b);
					}
					inFile.close();
					System.out.println("Sending completed!");
				}
				
			    if(bufferedReader.readLine().equals("put")) {
			    	System.out.println("preparing put file ---");
			    	InputStream inputStream = socket.getInputStream();
			    	
			    	
			    }

			}

		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}
}
