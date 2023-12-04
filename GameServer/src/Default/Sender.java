package Default;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Sender extends Thread {
	

	ServerSocket serverSocket;
	Socket socket;
	int port;
	
	private static int RIGHT_NUMBER;
	private static final int MAX_NUMBER_TRYING=5;
	
	private static final int TRY_AGAIN=0;
	private static final int YOU_WIN=1;
	private static final int YOU_LOSED=2;
	
	

	public Sender(int rightnumber, int serverPort) throws IOException {
		this.RIGHT_NUMBER=rightnumber;
		this.socket=new ServerSocket(serverPort).accept();
		this.port=serverPort;
		
	}
	@Override
	public void run() {
		try {
			initServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private void initServer() throws IOException {

;
		for(int i=1;i<10;i++) {
			int receivedMessage=doReceiveInt(socket);
			if(initSend(receivedMessage,socket,i)) {
				break;
			}
		
		}
		
	}
	private static boolean initSend(int receivedMessage, Socket socket, int counter) throws IOException {
		if(receivedMessage==RIGHT_NUMBER) {
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
    private static void doSendInt(Socket socket, int value) throws IOException {
        DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
        outToServer.writeInt(value);
    }
	private static int doReceiveInt(Socket socket) throws IOException {
        DataInputStream inFromServer = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        return inFromServer.readInt();
    }
	
}
