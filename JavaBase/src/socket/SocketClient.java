package socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class SocketClient {
	public static void main(String[] args){
		for(int i=1;i<=10;i++){
		    Socket socket=null;
			try {
				socket = new Socket("localhost",80);
				//得到输出流,向服务器发送数据
			    PrintWriter pr=new PrintWriter(socket.getOutputStream());
			    pr.print("I am the "+i+" client");
			    pr.flush();
			    socket.shutdownOutput();
			    
			    //得到输入流
			    BufferedReader bf=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			    String str=null;
			    while((str=bf.readLine())!=null){
			    	System.out.println(str);
			    }
			
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			finally {
//			    try {
//					socket.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
	    }
		
	}
}
