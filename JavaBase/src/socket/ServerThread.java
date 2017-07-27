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
					//�õ��������������ͻ��˷�����������
					BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String str;
					while((str=br.readLine())!=null){
				    	System.out.println(str);
				    }
					
					//�õ����������ͻ��˷�������
					PrintWriter pr=new PrintWriter(socket.getOutputStream());
					pr.print("�յ����� "+str);
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