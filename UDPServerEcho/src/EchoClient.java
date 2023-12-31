

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class EchoClient {
	private DatagramSocket socket;
	private InetAddress address;
	private byte[] buf;
	
	public EchoClient() {
		try {
			socket=new DatagramSocket();
			address=InetAddress.getByName("localhost");
		} catch (SocketException | UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		EchoClient client=new EchoClient();
		client.sendEcho("hello server");
		client.sendEcho("server is working");
		new EchoServer().start();
        client.close();
	}
	public String sendEcho(String msg) throws IOException {
		buf=msg.getBytes();
		DatagramPacket packet=new DatagramPacket(buf, buf.length, address, 4445);
		try {
			socket.send(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		packet=new DatagramPacket(buf, buf.length);
		socket.receive(packet);
		String received=new String(packet.getData(),0,packet.getLength());
        return received;
	}
	public void close() {
		socket.close();
	}

}
