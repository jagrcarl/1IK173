package ZString;

/**
 * A string class for demonstrating JUnit.
 *
 * @author lars.karlsson
 *
 */
public class ZString {
	
	/**
	 * The string's content.
	 */
	char[] data;
	
	/**
	 * 
	 */
	public ZString()
	{
		data = null;
	}
	
	/**
	 * Create an instance using some content.
	 * @param str The content.
	 */
	public ZString(char[] str) {
		data = new char[str.length];
		
		for (int i = 0; i < str.length; i++) {
			data[i] = str[i];
		}
		
	}

	/**
	 * Append a string to this instance.
	 * @param str String to append.
	 */
	public void append(ZString str) {
		
		if (str.data == null) {
			return;
		}
		
		char[] newData = new char[data.length + str.data.length];
		
		for (int i = 0; i < data.length; i++) {
			newData[i] = data[i];
		}
		
		for (int i = 0; i < str.data.length; i++) {
			newData[data.length+i] = str.data[i];
		}
		
		data = newData;
		
	}
	
}
