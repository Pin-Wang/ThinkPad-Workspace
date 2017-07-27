package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

	 public class ServerThread implements Runnable{
		 private Socket socket;
		 public ServerThread(Socket socket){
			 this.socket=socket;
		}
		 public void run() {
				try {
					//得到输入流，读出客户端发过来的数据
					BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String str;
					while((str=br.readLine())!=null){
				    	System.out.println(str);
				    }
					
					//得到输出流，向客户端发送数据
					PrintWriter pr=new PrintWriter(socket.getOutputStream());
					pr.print("收到内容 "+str);
					pr.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				finally {
					try {
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}
	}