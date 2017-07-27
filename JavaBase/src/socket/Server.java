package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
	public static void main(String[] args) throws IOException{
		ServerSocket serverSocket=new ServerSocket(80);
		while(true){
			Socket socket;
			try {
				socket = serverSocket.accept();
				ServerThread serverThread=new ServerThread(socket);
				new Thread(serverThread).start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
}


