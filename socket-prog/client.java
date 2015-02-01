import java.io.*;
import java.net.*;
public class client{
	public static void main(String args[]) throws Exception{
		String sent;
		String modified;
		while(true){
		BufferedReader infromuser= new BufferedReader(new InputStreamReader(System.in));
		Socket s = new Socket("localhost",6789);
		DataOutputStream toserver= new DataOutputStream(s.getOutputStream());
		BufferedReader fromserver=new BufferedReader(new InputStreamReader(s.getInputStream()));
		sent=infromuser.readLine();
		toserver.writeBytes(sent+'\n');
		modified=fromserver.readLine();
		System.out.println("From server: " + modified);
			
		}
	
	}
}
