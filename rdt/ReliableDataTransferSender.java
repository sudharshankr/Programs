
public class ReliableDataTransferSender {

	UnreliableLineSender udtSender;

	public ReliableDataTransferSender() {
		udtSender = new UnreliableLineSender(0, 0,this);
	}

	public void send(String data) {
		udtSender.udtSend(data);
	}

	public void receive(String data) {
		System.out.println(data);
	}
}
