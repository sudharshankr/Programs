import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class UnreliableLineSender  implements Runnable {

	private double packetloss;
	private double biterrors;
	private ReliableDataTransferSender sender;

	private Socket socket;


	public UnreliableLineSender(double packetloss, double biterrors, ReliableDataTransferSender sender) {
		this.packetloss = packetloss;
		this.biterrors = biterrors;
		this.sender = sender;

		new Thread(this).start();

	}

	public void udtSend(String data) {
		if( 0.00000001 + Math.random() > packetloss) {
			// Must send it, it is not lost

			if( Math.random() < biterrors ) {
				data = Utils.createBitError(data);
			}

			// Send the packet
			System.out.println("Skal sende: " + data);

			Socket socket = null;
			try {
				socket = new Socket(Configuration.RECEIVER_IP, Configuration.RECEIVER_PORT);
				PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader( socket.getInputStream()));

				writer.println(data);
				//System.out.println("echo: " + in.readLine());
				socket.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		BufferedReader in = null;
		PrintStream out = null;

		ServerSocket connection = null;
		try {
			connection = new ServerSocket( Configuration.SENDER_PORT );
			while(true) {
				socket = connection.accept();
				System.out.println("Startet send tråd");

				in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
				out = new PrintStream(socket.getOutputStream());

				String data;
				while ((data = in.readLine()) != null) {
					sender.receive(data);
					//out.print(reply + "\n");
				}
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
