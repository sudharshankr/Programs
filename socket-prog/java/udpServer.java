import java.io.*;
import java.net.*;
class udpServer{ 
	public static void main(String args[]) throws Exception{
	 	String in;
		String out;
		DatagramSocket s1 = new DatagramSocket(6789);
		while(true){
		byte[] recD=new byte[1024];
		byte[] sendD=new byte[1024];
		DatagramPacket recP=new DatagramPacket(recD,recD.length);
		s1.receive(recP);
		in=new String(recP.getData());
		InetAddress IPa = recP.getAddress();
		int port=recP.getPort();
		out=in.toUpperCase();
		sendD=out.getBytes();
		DatagramPacket sendP = new DatagramPacket(sendD,sendD.length,IPa,port);
		s1.send(sendP);
		}
	}
}
		
		
