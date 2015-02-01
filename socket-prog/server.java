import java.io.*;
import java.net.*;

public class server{
	public static void main (String args[]) throws Exception{
		String in;
		String out;
		ServerSocket s1 = new ServerSocket(6789);
		while(true){
			Socket conn= s1.accept();
			BufferedReader fromclient= new BufferedReader(new InputStreamReader(conn.getInputStream()));
	 		BufferedReader inp=new BufferedReader(new InputStreamReader(System.in));
			DataOutputStream toclient= new DataOutputStream(conn.getOutputStream());
			in=fromclient.readLine();
			System.out.println("from Client: "+ in);
		 	out=inp.readLine();
			toclient.writeBytes(out+'\n');
		}
	}
}
		
