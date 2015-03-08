import java.util.Random;

public class Utils {

	private static Random random = new Random();

	public static String createBitError(String data) {
		String result = data;
		int offset = random.nextInt(data.length());
		for( int i = 0; i < data.length() -1 ; i++ ) {
			int location = (i+ offset) % data.length();
			char c = data.charAt( (i+ offset) % data.length());
			if( (c >= 'a' && c < 'z') || (c >= 'A' && c < 'Z') || ( c >= '0' && c < '9') ) {
				char error = (char)  (c + 1);
				result = "" + data.substring(0, location) + error  + data.substring(location+1, data.length() );
				break;
			}
		}
		return result;
	}




}
