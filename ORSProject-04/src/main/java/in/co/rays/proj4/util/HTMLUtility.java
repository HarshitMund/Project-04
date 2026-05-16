package in.co.rays.proj4.util;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import in.co.rays.proj4.bean.DropdownListBean;
import in.co.rays.proj4.model.RoleModel;

/**
 * Utility factory used to compile structural UI block structures like standard dynamic HTML Select lists.
 * @author Harshit
 */
public class HTMLUtility {

	/**
	 * Constructs an HTML Select element drop-down using a simple data map structure.
	 * * @param name tag string identification name
	 * @param selectedVal item matching this key identifier is marked selected
	 * @param map dataset map
	 * @return safe text representation string of HTML Select dropdown component
	 */
	public static String getList(String name, String selectedVal, HashMap<String, String> map) {

		StringBuffer sb = new StringBuffer(
				"<select style=\"width: 169px;text-align-last: center;\"; class='form-control' name='" + name + "'>");

		sb.append("\n<option selected value=''>-------------Select-------------</option>");

		Set<String> keys = map.keySet();
		String val = null;

		for (String key : keys) {
			val = map.get(key);
			if (key.trim().equals(selectedVal)) {
				sb.append("\n<option selected value='" + key + "'>" + val + "</option>");
			} else {
				sb.append("\n<option value='" + key + "'>" + val + "</option>");
			}
		}
		sb.append("\n</select>");
		return sb.toString();
	}

	/**
	 * Constructs an HTML Select element drop-down using objects that implement {@link DropdownListBean}.
	 * * @param name tag string identification name
	 * @param selectedVal object entry matching key is marked selected
	 * @param list objects implementation list collection container
	 * @return safe text representation string of HTML Select dropdown component
	 */
	public static String getList(String name, String selectedVal, List list) {

		// Collections.sort(list);

		List<DropdownListBean> dd = (List<DropdownListBean>) list;

		StringBuffer sb = new StringBuffer("<select style=\"width: 169px;text-align-last: center;\"; "
				+ "class='form-control' name='" + name + "'>");

		sb.append("\n<option selected value=''>-------------Select-------------</option>");

		String key = null;
		String val = null;

		for (DropdownListBean obj : dd) {
			key = obj.getKey();
			val = obj.getValue();

			if (key.trim().equals(selectedVal)) {
				sb.append("\n<option selected value='" + key + "'>" + val + "</option>");
			} else {
				sb.append("\n<option value='" + key + "'>" + val + "</option>");
			}
		}
		sb.append("\n</select>");
		return sb.toString();
	}

	/**
	 * Test method simulating dropdown population targeting direct map collections.
	 */
	public static void testGetListByMap() {

		HashMap<String, String> map = new HashMap<>();
		map.put("male", "male");
		map.put("female", "female");

		String selectedValue = "male";
		String htmlSelectFromMap = HTMLUtility.getList("gender", selectedValue, map);

		System.out.println(htmlSelectFromMap);
	}

	/**
	 * Test method simulating dynamic select compilation tracking database Models processing.
	 * * @throws Exception generic execution exceptions handled cleanly
	 */
	public static void testGetListByList() throws Exception {

		RoleModel model = new RoleModel();

		// UserModel model = new UserModel();

		List list = model.list();

		String selectedValue = "1";

		String htmlSelectFromList = HTMLUtility.getList("role", selectedValue, list);

		System.out.println(htmlSelectFromList);
	}

	/**
	 * Primary testing terminal initialization loop context.
	 * * @param args initialization runtime arguments
	 * @throws Exception exceptions piped outwards downstream safely
	 */
	public static void main(String[] args) throws Exception {

		// testGetListByMap();

		testGetListByList();

	}
}