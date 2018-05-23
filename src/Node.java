/*
 *
 *
 * @ Author Kevin Adamczewski
 */
public class Node {

	private Integer x = null;
	private Integer y = null;
	private String name = null;

	Node(String name)
	{
		this.name =name;
	}


	Integer getX() {
		return x;
	}

	void setX(Integer x) {
		this.x = x;
	}

	Integer getY() {
		return y;
	}

	void setY(Integer y) {
		this.y = y;
	}
	/*
	String getName() {
		return name;
	}
	void setName(String name) {
		this.name = name;
	}
	 */

	public String toString()
	{
		String result = null;

		if (name == null) {
			result = "kein Name vorhanden";
		} else {
			result = name;
		}

		if (x != null && y != null) {
			result = result + "(x=" + x + ", " + "y=" + y + ")";
		}
		return result;
	}


}