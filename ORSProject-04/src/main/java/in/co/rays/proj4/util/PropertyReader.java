package in.co.rays.proj4.util;

import java.util.ResourceBundle;

/**
 * Controller class executing variable configuration string data Extractions directly from bundle resource systems.
 * * @author Harshit
 */
public class PropertyReader {

	/** Resource configuration target pointer identifier */
	private static ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.proj4.bundle.system");

	/**
	 * Resolves static system text value associated with key identifier parameters.
	 * * @param key identifier configuration target lookup parameter string
	 * @return resolved system value text parameters, or key string value if unresolved errors happen
	 */
	public static String getValue(String key) {

		String val = null;

		try {
			val = rb.getString(key); // {0} is required
		} catch (Exception e) {
			val = key;
		}
		return val;
	}

	/**
	 * Pulls specified property contents matching token values and replaces placeholder index parameter string indicator marker (`{0}`).
	 * * @param key resource configurations database identifier pointer lookup
	 * @param param parameters to dynamically inject inside key placeholders matches
	 * @return modified textual representation sequence content string
	 */
	public static String getValue(String key, String param) {
		String msg = getValue(key); // {0} is required
		msg = msg.replace("{0}", param);
		return msg;
	}

	/**
	 * Pulls specific configurations properties matching multiple tracking indexes markers (`{0}`, `{1}`, etc.).
	 * * @param key configuration parameter entry identification target lookup keyword
	 * @param params parameters strings array tracking insertion indexes variables sequence properties
	 * @return compilation result string
	 */
	public static String getValue(String key, String[] params) {
		String msg = getValue(key); // {0} and {1} are required.
		for (int i = 0; i < params.length; i++) {
			msg = msg.replace("{" + i + "}", params[i]);
		}
		return msg;
	}

	/**
	 * Local test main loop verifying functionality of multi-parameter substitutions.
	 * * @param args command-line initialization runtime strings parameters array
	 */
	public static void main(String[] args) {

		System.out.println("Single key example:");
		System.out.println(PropertyReader.getValue("error.require"));

		System.out.println("\nSingle parameter replacement example:");
		System.out.println(PropertyReader.getValue("error.require", "loginId"));

		System.out.println("\nMultiple parameter replacement example:");
		String[] params = { "Roll No", "Student Name" };
		System.out.println(PropertyReader.getValue("error.multipleFields", params));
	}
}