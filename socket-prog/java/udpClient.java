import java.io.*;
import java.net.*;

class udpClient{
	public static void main(String args[]) throws Exception{
		String in;
		String out;
		DatagramSocket s = new DatagramSocket();
		BufferedReader fu = new BufferedReader(new InputStreamReader(System.in));
		InetAddress IPA = InetAddress.getByName("localhost");
		in = fu.readLine();
		byte[] sendD= new byte[1024];
		byte[] recD = new byte[1024];
	
		sendD= in.getBytes();
		DatagramPacket sendP= new DatagramPacket(sendD,sendD.length,IPA,6789);
		s.send(sendP);
		DatagramPacket recP = new DatagramPacket(recD,recD.length);
		s.receive(recP);
		out = new String(recP.getData());
		System.out.println(out+'\n');
		s.close();
	}
}


