

public class ReliableDataTransferReceiver {

	private MainReceiver receiver;
	private UnreliableLineReceiver unreliableLineReceiver;

	public ReliableDataTransferReceiver(MainReceiver receiver) {
		this.receiver = receiver;
		this.unreliableLineReceiver = new UnreliableLineReceiver(0,0,this);
	}

	public String receive(String data) {
		// TODO Handle different scenaries here
		receiver.deliverData(data);
		return "";
	}





}
