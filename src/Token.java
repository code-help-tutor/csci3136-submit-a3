WeChat: cstutorcs
QQ: 749389476
Email: tutorcs@163.com

/**
 * 
 * According to the requirement of assignment: Then the scanner should produce
 * the sequence of integer values 1 2 1 2 2 2 3 ... for the program above, along
 * with the associated strings of the different tokens.
 * 
 * This Java class is for storing the token information.
 */
public class Token {
	private int key;
	// token order in TOY source code
	private int order;
	// token name
	private String name;
	// if the token is keyword, value is the same with name, otherwise, they are
	// different: for exmple, int(0)
	private String value;

	public Token(int order, int key, String name, String value) {
		super();
		this.key = key;
		this.order = order;
		this.name = name;
		this.value = value;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getOrder() {
		return order;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		String ret = "";

		ret = name + "(" + value + ")";

		return ret;
	}

}
