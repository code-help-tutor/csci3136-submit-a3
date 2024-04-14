WeChat: cstutorcs
QQ: 749389476
Email: tutorcs@163.com

/*
 * 
 * In order to store the position of one word in one line, to create this Java
 * class.
 */
public class WordDefinition {

	private int line;
	private int position;
	private String word;

	public int getLine() {
		return line;
	}

	public int getPosition() {
		return position;
	}

	public String getWord() {
		return word;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public String toString() {
		return "WordDefinition [line=" + line + ", position=" + position
				+ ", word=" + word + "]";
	}

	public WordDefinition(int line, int position, String word) {
		super();
		this.line = line;
		this.position = position;
		this.word = word;
	}

}
