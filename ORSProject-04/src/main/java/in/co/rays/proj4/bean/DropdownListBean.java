package in.co.rays.proj4.bean;

/**
 * DropdownListBean interface is used to populate dropdown lists.
 * <p>
 * Any bean implementing this interface can provide:
 * <ul>
 * <li>A key value</li>
 * <li>A display value</li>
 * </ul>
 * 
 * Example:
 * 
 * <pre>
 * &lt;option value="1"&gt;Admin&lt;/option&gt;
 * </pre>
 * 
 * Here:
 * <ul>
 * <li>Key = 1</li>
 * <li>Value = Admin</li>
 * </ul>
 * 
 * @author Harshit
 */
public interface DropdownListBean {

	/**
	 * Returns key value of dropdown option.
	 * 
	 * @return key
	 */
	public String getKey();

	/**
	 * Returns display value of dropdown option.
	 * 
	 * @return value
	 */
	public String getValue();

}