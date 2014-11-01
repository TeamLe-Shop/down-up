package downup.omnom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import downup.DownUp;

public class OmnomClient extends Thread {
	
	public Socket socket;
	public BufferedWriter writer;
	public BufferedReader reader;
	public DownUp downUp;
	
	public OmnomClient(DownUp downUp) {
		this.downUp = downUp;
	}
	
	public void run() {
		try {
			socket = new Socket("localhost", 7770);
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			reader = new BufferedReader(new  InputStreamReader(socket. getInputStream()));
			String line = "";
			DownUp.connectedToIRC = true;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("say")) {
					downUp.getServer().broadcastMessage("IRConsole: " + line.substring(4));
				}
			}
		} catch (IOException ioe) { 
			ioe.printStackTrace();
			DownUp.connectedToIRC = false;
		}
	}

	public void send(String info) {
		if (!DownUp.connectedToIRC) return;
		try {
			writer.write(info + "\r\n");
			writer.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
