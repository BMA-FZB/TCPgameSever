package Default;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	private static final int SERVER_PORT = 2020;
	
	private static final int RightNumber=(int)(Math.random()*11);
	
	private static final int MAX_CLIENTS = 5;

	
	

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

         ServerSocket serverSocket = new ServerSocket(SERVER_PORT);

         for (int i = 0; i < MAX_CLIENTS; i++) {
             Socket socket = serverSocket.accept();
             System.out.println("Client connected: " + socket.getInetAddress());

             Sender sender = new Sender((int) (Math.random() * 11), SERVER_PORT, socket);
             sender.start();
         }

         serverSocket.close();
		
	}
	/*
	
	
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
	*/

}
