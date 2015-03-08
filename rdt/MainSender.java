
public class MainSender {

	public static void main(String[] args) {
		new MainSender();
	}

	public MainSender() {

		ReliableDataTransferSender rdtSender = new ReliableDataTransferSender();
		String data = "The problem with UDP jokes: I don’t get half of them.";

		for(int i = 0; i < data.length(); i += 5) {
			String packetData = data.substring(i, Math.min(i+5,data.length()-1));
			//System.out.println(packetData);
			rdtSender.send(packetData);
		}

	}





}
