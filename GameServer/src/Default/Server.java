package Default;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static final int SERVER_PORT = 2020;
	
	private static final int RightNumber=(int)(Math.random()*11);
	private static final int MAX_NUMBER_TRYING=5;
	
	
	private static final int TRY_AGAIN=0;
	private static final int YOU_WIN=1;
	private static final int YOU_LOSED=2;
	
	
	

	public static void main(String[] args) {
		try {
			System.out.println("write number is "+RightNumber);
			initServer();

			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
    private static void initServer() throws IOException {

    	System.out.println("Server running on :" + SERVER_PORT);
    	

    	
    	Sender sender=new Sender( RightNumber, SERVER_PORT);
    	sender.start();

		
		
		
		
		
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
