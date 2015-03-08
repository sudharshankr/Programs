
public class MainReceiver {

	public static void main(String[] args) {
		new MainReceiver();

	}

	public MainReceiver() {
		ReliableDataTransferReceiver receiver = new ReliableDataTransferReceiver(this);
	}

	public void deliverData(String data) {
		System.out.print(data);
	}




}
