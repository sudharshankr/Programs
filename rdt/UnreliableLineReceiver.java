import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class UnreliableLineReceiver implements Runnable {

	private double packetloss;
	private double biterrors;
	private Socket socket;
	ReliableDataTransferReceiver receiver;

	public UnreliableLineReceiver(double packetloss, double biterrors, ReliableDataTransferReceiver receiver) {
		this.packetloss = packetloss;
		this.biterrors = biterrors;
		this.receiver = receiver;
		new Thread(this).start();

	}

	public void udtSend(String data) {
		if( 0.00000001 + Math.random() > packetloss) {
			// Must send it, it is not lost

			if( 0.00000001 + Math.random() < biterrors ) {
				data = Utils.createBitError(data);
			}

			// Send the packet
			//System.out.println("UnreliableLineReceiver->udtSend, Skal sende: " + data);

			Socket socket = null;
			try {
				socket = new Socket(Configuration.SENDER_IP, Configuration.SENDER_PORT);
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
			connection = new ServerSocket( Configuration.RECEIVER_PORT );
			while(true) {
				socket = connection.accept();
				in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
				out = new PrintStream(socket.getOutputStream());

				String data;
				while ((data = in.readLine()) != null) {
					String reply = receiver.receive(data);
					out.print(reply + "\n");
				}

				socket.close();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
