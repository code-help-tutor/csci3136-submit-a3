WeChat: cstutorcs
QQ: 749389476
Email: tutorcs@163.com


/**
 * 
 * Define all of the valid tokens in TOY
 */
public class TokenDefinition {
	public static final int TOY_FUN = 0;
	public static final int TOY_COFUN = 1;
	public static final int TOY_TYPE = 2;
	public static final int TOY_VAR = 3;
	public static final int TOY_END = 4;
	public static final int TOY_LAMBDABEGIN = 5;
	public static final int TOY_LAMBADAEND = 6;
	public static final int TOY_LAMBDABEGINOR = 7;
	public static final int TOY_LAMBADAENDOR = 8;

	public static final int TOY_INT = 12;
	public static final int TOY_FLOAT = 13;
	public static final int TOY_CHAR = 14;
	public static final int TOY_STRING = 15;

	public static final int TOY_ID = 16;
	public static final int TOY_SYMBOL = 17;
	public static final int TOY_REFERENCE = 18;

	public static int getTokenKeyByName(String name) {
		int ret = -1;
		if (name.equals("fun"))
			return TOY_FUN;
		if (name.equals("cofun"))
			return TOY_COFUN;
		if (name.equals("type"))
			return TOY_TYPE;
		if (name.equals("var"))
			return TOY_VAR;
		if (name.equals("."))
			return TOY_END;

		if (name.equals("["))
			return TOY_LAMBDABEGIN;
		if (name.equals("]"))
			return TOY_LAMBADAEND;
		if (name.equals("[|"))
			return TOY_LAMBDABEGINOR;
		if (name.equals("|]"))
			return TOY_LAMBADAENDOR;

		return ret;
	}

	public static String getTokenNameByKey(int key) {
		String ret = "";
		switch (key) {
		case TOY_FUN:
			ret = "fun";
			break;
		case TOY_COFUN:
			ret = "cofun";
			break;
		case TOY_TYPE:
			ret = "type";
			break;
		case TOY_VAR:
			ret = "var";
			break;
		case TOY_END:
			ret = "end";
			break;

		case TOY_LAMBDABEGIN:
			ret = "lambdabegin";
			break;

		case TOY_LAMBADAEND:
			ret = "lambdaend";
			break;

		case TOY_LAMBDABEGINOR:
			ret = "lambdaorbegin";
			break;

		case TOY_LAMBADAENDOR:
			ret = "lambdaorend";
			break;

		case TOY_INT:
			ret = "int";
			break;
		case TOY_FLOAT:
			ret = "float";
			break;
		case TOY_CHAR:
			ret = "char";
			break;
		case TOY_STRING:
			ret = "string";
			break;

		case TOY_ID:
			ret = "id";
			break;
		case TOY_SYMBOL:
			ret = "symbol";
			break;
		case TOY_REFERENCE:
			ret = "reference";
			break;

		}
		return ret;
	}

	public static String getTokenValueByKey(int key) {
		String ret = "";
		switch (key) {
		case TOY_FUN:
			ret = "fun";
			break;
		case TOY_COFUN:
			ret = "cofun";
			break;
		case TOY_TYPE:
			ret = "type";
			break;
		case TOY_VAR:
			ret = "var";
			break;
		case TOY_END:
			ret = ".";
			break;

		case TOY_LAMBDABEGIN:
			ret = "[";
			break;

		case TOY_LAMBADAEND:
			ret = "]";
			break;

		case TOY_LAMBDABEGINOR:
			ret = "[|";
			break;

		case TOY_LAMBADAENDOR:
			ret = "|]";
			break;

		case TOY_INT:
			ret = "int";
			break;
		case TOY_FLOAT:
			ret = "float";
			break;
		case TOY_CHAR:
			ret = "char";
			break;
		case TOY_STRING:
			ret = "string";
			break;

		case TOY_ID:
			ret = "id";
			break;
		case TOY_SYMBOL:
			ret = "symbol";
			break;
		case TOY_REFERENCE:
			ret = "reference";
			break;

		}
		return ret;
	}

	private static String[] key_words = { "fun", "cofun", "type", "var", ".",
			"[", "]", "[|", "|]" };

	public static int isKeyWord(String token) {
		int ret = -1;
		for (int i = 0; i < key_words.length; i++) {
			if (token.equals(key_words[i])) {
				ret = i;
				break;
			}
		}
		return ret;
	}

	public static int isKeyWord(int key) {
		if (key == TOY_FUN || key == TOY_COFUN || key == TOY_TYPE
				|| key == TOY_VAR || key == TOY_END || key == TOY_LAMBDABEGIN
				|| key == TOY_LAMBADAEND || key == TOY_LAMBDABEGINOR
				|| key == TOY_LAMBADAENDOR) {
			return key;
		}
		return -1;
	}
}
