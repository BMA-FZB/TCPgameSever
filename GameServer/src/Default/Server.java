package Default;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static final int SERVER_PORT = 2020;
	
	private static final int RightNumber=(int)(Math.random()*10);
	private static final int MAX_NUMBER_TRYING=5;
	
	
	private static final int TRY_AGAIN=0;
	private static final int YOU_WIN=1;
	private static final int YOU_LOSED=2;
	
	
	

	public static void main(String[] args) {
		try {

			initServer();

			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
    private static void initServer() throws IOException {

    	System.out.println("Server running on :" + SERVER_PORT);
    	
		Socket socket=new ServerSocket(SERVER_PORT).accept();
		
		
		for(int i=1;i<10;i++) {
		int receivedMessage=doReceiveInt(socket);
		if(initSend(receivedMessage,socket,i)) {
			break;
		}
		
		}
		
		
	}
	private static boolean initSend(int receivedMessage, Socket socket, int counter) throws IOException {
		if(receivedMessage==RightNumber) {
			doSendInt(socket, YOU_WIN);
			return true;
		}
		if(counter<MAX_NUMBER_TRYING) {
			doSendInt(socket, TRY_AGAIN);
			return false;
		}
		else
			doSendInt(socket, YOU_LOSED);
			return true;
		
	}
	
	
	
	private static void doSend(Socket socket, String message) throws IOException {
        DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
        outToServer.writeBytes(message + "\n");
    }

    private static String doReceive(Socket socket) throws IOException {
        DataInputStream inFromServer = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        return inFromServer.readLine();
    }
    private static void doSendInt(Socket socket, int value) throws IOException {
        DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
        outToServer.writeInt(value);
    }

    private static int doReceiveInt(Socket socket) throws IOException {
        DataInputStream inFromServer = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        return inFromServer.readInt();
    }
	

}
