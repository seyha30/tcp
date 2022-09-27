import java.io.*;
import java.net.*;

import javax.swing.text.GapContent;

public class TCPFileClient {
	public static void main(String[] args) throws IOException {

		if (!checkArgs(args)) {
			return;
		}

		String ip = "localhost";
		String command = args[0].toLowerCase();
		Socket socket = null;
		PrintWriter out = null;
		final int PORT = 9999;
		BufferedReader in = null;
		socket = new Socket(ip, PORT);
			switch (command) {
			case "get":
				String filename = args[1];
				System.out.println("todo get");
				try {
					String savedfilename = System.currentTimeMillis() + "_" + filename;
					socket.setSoTimeout(3 * 1000); // Wait for 1s for reading timeout
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					out = new PrintWriter(socket.getOutputStream(), true);
					// Send requested filename to server
					out.println(filename);
					// Wait for server replies whether file exists or returns file size
					long filesize = Long.parseLong(in.readLine());
					if (filesize < 0) {
						System.out.println("Oop! File does not exist on server!");
						return;
					} else {
						System.out.println("File size = " + filesize + " bytes");
					}
					out.println("OK");
					System.out.println("Start receiving " + filename + " from server");
					FileOutputStream outFile = new FileOutputStream(savedfilename);
					try {
						InputStream inSocket = socket.getInputStream();
						int b;
						long l = 0;
						byte[] buf = new byte[1024];
						while ((b = inSocket.read(buf, 0, 1024)) != -1) {
							l += b;
							outFile.write(buf, 0, b);
							if (l == filesize)
								break;
						}
						System.out.println("Receiving completed!");
					} catch (SocketTimeoutException ste) {
						System.out.println("[Error] Receiving timeout!!!!");
					} finally {
						outFile.close();
					}
				} catch (IOException ioe) {
					System.out.println(ioe);
				}
				break;
			case "put":
				System.out.println("todo upload");

				break;
			case "quit":
				socket.close();
				System.out.println("quit socket");
				break;
			default:
				System.out.println("command not supported");
				break;
			}
		

	}

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

}
