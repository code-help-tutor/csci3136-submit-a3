WeChat: cstutorcs
QQ: 749389476
Email: tutorcs@163.com


import java.util.ArrayList;

/*
 * This class is designed to handle one input regular expression, and get the
 * right character in one regular expression.
 * 
 */
public class RegexProvider {

    private RegexFlag[] arr_flags = new RegexFlag[TOYConstants.DEFAULT_SIZE];
    public RegexFlag currentToken = RegexFlag.END_OF_PHRASE;
    ArrayList<String> regext_toy_set = new ArrayList<String>();
    private int number_of_regex = 0;
    private String current_regular_str = "";
    private int charIndex = 0;
    private boolean isQuoted = false;
    private boolean isSpecificChar = false;
    private int provider;

    public RegexProvider(ArrayList<String> regext_toy_set) {
        arr_flags = RegexUtils.empteyList();
        this.regext_toy_set = regext_toy_set;
    }

    public boolean checkRegexFlag(RegexFlag t) {
        return currentToken == t;
    }

    public int getProvider() {
        return provider;
    }

    public String getCurrentRegularStr() {
        return current_regular_str;
    }

    public RegexFlag getCurrentToken() {
        return currentToken;
    }

    private char get(int position) {
        return current_regular_str.charAt(position);
    }

    public RegexFlag moveWithRightStep() {
        if (currentToken == RegexFlag.END_OF_PHRASE) {
            if (number_of_regex >= regext_toy_set.size()) {
                currentToken = RegexFlag.END_OF_WHOLE;
                return currentToken;
            } else {
                current_regular_str = regext_toy_set.get(number_of_regex);
                number_of_regex++;
            }
        }

        if (charIndex >= current_regular_str.length()) {
            charIndex = 0;
            currentToken = RegexFlag.END_OF_PHRASE;
            return currentToken;
        }

        if (get(charIndex) == TOYConstants.STR_FLAG) {
            isQuoted = !isQuoted;
            charIndex++;
        }

        isSpecificChar = (get(charIndex) == TOYConstants.SLASH_FLAG);
        if (isSpecificChar && get(charIndex + 1) != TOYConstants.STR_FLAG && isQuoted == false) {
            charIndex++;
            provider = get(charIndex);
        } else {
            if (isSpecificChar && get(charIndex + 1) == TOYConstants.STR_FLAG) {
                charIndex = charIndex + 2;
                provider = TOYConstants.STR_FLAG;
            } else {
                provider = get(charIndex);
                charIndex++;
            }
        }
        if (isQuoted || isSpecificChar) {
            currentToken = RegexFlag.DEFAULT_CHAR;
        } else {
            currentToken = arr_flags[provider];
        }
        return currentToken;
    }
}
