import java.io.*;
import java.net.*;

public class TCPFileClient {
	public static void main(String[] args) {

		if (!checkArgs(args)) {
			return;
		}

		String ip = args[0];
		String command = args[0].toLowerCase();
		if (command.length() > 0) {
			System.out.println("command line " + command.length());

		} else {

		}

		switch (command) {
		case "get":
			System.out.println("do get");
			break;
		case "put":
			System.out.println("do put");
			break;
		default:
			System.out.println("do quit");
			break;
		}

//		String fileName = args[1];

//		String savedfilename = "test_" + fileName;
//		final int PORT = 9999;
//		final String IP = "localhost";
//        System.out.println(command);
//		System.out.println(fileName);

		/*
		 * 
		 * Socket socket = null; PrintWriter out = null; BufferedReader in = null; try{
		 * socket = new Socket(IP, PORT); socket.setSoTimeout(3*1000); //Wait for 1s for
		 * reading timeout in = new BufferedReader(new
		 * InputStreamReader(socket.getInputStream())); out = new
		 * PrintWriter(socket.getOutputStream(), true);
		 * 
		 * //Send requested filename to server out.println(filename);
		 * 
		 * //Wait for server replies whether file exists or returns file size long
		 * filesize = Long.parseLong(in.readLine()); if(filesize < 0){
		 * System.out.println("Oop! File does not exist on server!"); return; }else{
		 * System.out.println("File size = " + filesize + " bytes"); }
		 * 
		 * out.println("OK");
		 * 
		 * System.out.println("Start receiving " + filename + " from server");
		 * FileOutputStream outFile = new FileOutputStream(savedfilename); try{
		 * InputStream inSocket = socket.getInputStream(); int b; long l=0; byte[] buf =
		 * new byte[1024]; while((b = inSocket.read(buf, 0, 1024)) != -1){ l += b;
		 * outFile.write(buf, 0, b); if(l == filesize) break; }
		 * System.out.println("Receiving completed!"); }catch(SocketTimeoutException
		 * ste){ System.out.println("[Error] Receiving timeout!!!!"); }finally{
		 * outFile.close(); } }catch(IOException ioe){ System.out.println(ioe); }
		 * 
		 */

	}

	private static boolean checkArgs(String[] args) {
		if (args.length <= 0) {
			System.out.println("Incorrect command");
			return false;
		}
		return true;
	}

}
