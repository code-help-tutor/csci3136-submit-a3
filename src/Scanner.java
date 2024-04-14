WeChat: cstutorcs
QQ: 749389476
Email: tutorcs@163.com


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/*
 * This is the main class for assignment 3 of CSCI3136 CSCI 3136: Principles of
 * Programming Languages.
 * 
 * A main method to be provided, it will read a file which is assigned by
 * parameter, and read one TOY source code.And then output the whole tokens.
 * 
 * According to the introduction in class, a basic method for lexical analysis
 * is: convert regular expressions to NFA, then to DFA,after minimum DFA, to
 * evaluate each word to get all tokens.
 * 
 * The reason to from NFA to DFA is the time complexity of DFA is better. In
 * this assignment, we try to use NFA to evaluate each word directly.
 */
public class Scanner {

	private String fileName = "";
	private ArrayList<String> lines;
	private ArrayList<Token> tokens;
	private ArrayList<WordDefinition> words;
	private int position;
	private int size;

	// get the all tokens from evaluation result of NFA
	private void generateTokens() {
		String word = "";
		int key_word_index = -1;
		for (int i = 0; i < words.size(); i++) {
			WordDefinition wf = words.get(i);
			word = wf.getWord();
			// if the word is one keyword in TOY
			key_word_index = TokenDefinition.isKeyWord(word);
			if (key_word_index >= 0) {
				Token token = new Token(i, key_word_index,
						TokenDefinition.getTokenNameByKey(key_word_index),
						TokenDefinition.getTokenValueByKey(key_word_index));
				// generate token directly
				tokens.add(token);
			} else {
				// otherwise, need to create NFA to evaluate this word
				int key_index = TokenTypeFinder.getTokenKeyByWord(word);
				// if this word is not accepted by all NFA, means it is an
				// illegal word
				if (key_index == -1) {
					int position = wf.getPosition();
					System.out.println("Illegal word found:" + word + ",line:"
							+ wf.getLine() + "," + position + "\n");
				} else {
					// if it's a valid token in TOY
					Token token = null;
					// using a Token Java object to store each token
					switch (key_index) {
					case TokenDefinition.TOY_INT:
						token = new Token(i, key_index,
								TokenDefinition.getTokenNameByKey(key_index),
								word);
						break;
					case TokenDefinition.TOY_FLOAT:
						token = new Token(i, key_index,
								TokenDefinition.getTokenNameByKey(key_index),
								word);
						break;
					case TokenDefinition.TOY_CHAR:
						token = new Token(i, key_index,
								TokenDefinition.getTokenNameByKey(key_index),
								word);
						break;
					case TokenDefinition.TOY_STRING:
						token = new Token(i, key_index,
								TokenDefinition.getTokenNameByKey(key_index),
								word);
						break;
					case TokenDefinition.TOY_ID:
						token = new Token(i, key_index,
								TokenDefinition.getTokenNameByKey(key_index),
								word);
						break;
					case TokenDefinition.TOY_SYMBOL:
						token = new Token(i, key_index,
								TokenDefinition.getTokenNameByKey(key_index),
								word);
						break;
					case TokenDefinition.TOY_REFERENCE:
						token = new Token(i, key_index,
								TokenDefinition.getTokenNameByKey(key_index),
								word);
						break;
					}
					tokens.add(token);
				}
			}
		}
		size = tokens.size();
	}

	// in constructor, read TOY file and get all potential tokens
	public Scanner(String fileName) {
		this.fileName = fileName;
		java.util.Scanner scanner;
		try {
			// start to read TOY source code file
			scanner = new java.util.Scanner(new File(fileName));
			lines = new ArrayList<String>();
			words = new ArrayList<WordDefinition>();
			// using an array list to store all lines
			while (scanner.hasNext()) {
				lines.add(scanner.nextLine());
			}
			scanner.close();
			tokens = new ArrayList<Token>();
			position = 0;
			int number_of_line = 1;
			int position_in_line = 0;
			// start to split each line to words
			for (String line : lines) {
				// System.out.println(line);
				ArrayList<String> tmp = new ArrayList<String>();
				tmp = splitTokens(line);
				for (String str : tmp) {
					position_in_line = line.indexOf(str);
					// using WordDefinition to indicate each word position in
					// source code
					WordDefinition wf = new WordDefinition(number_of_line,
							position_in_line, str);
					words.add(wf);
				}
				number_of_line++;
			}
			// start to generate tokens from words
			generateTokens();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// provide method to know if more tokens
	public boolean hasNext() {
		boolean ret = true;
		if (position < size)
			ret = true;
		else
			ret = false;
		return ret;
	}

	// get the next available token
	public String next() {
		String ret = "";
		ret = tokens.get(position).toString();
		position++;
		return ret;
	}

	/*
	 * Test using this main method
	 */
	public static void main(String[] args) {
		System.out.println(
				"Please enter the name of a TOY program file (by default is test.toy): ");
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		String fileName = "test.toy";
		fileName = scanner.nextLine();
		if (fileName == null || fileName.equals(""))
			fileName = "test.toy";
		scanner.close();
		Scanner sc = new Scanner(fileName);
		while (sc.hasNext()) {
			System.out.print(sc.next() + " ");
		}
	}

	// remove comment parts from TOY source code
	private ArrayList<String> splitTokens(String line) {
		ArrayList<String> list = new ArrayList<String>();
		String[] words = line.split(" ");
		for (String word : words) {
			word = word.trim();
			if (!word.equals("//"))
				list.add(word);
			else
				break;
		}
		return list;
	}

}
