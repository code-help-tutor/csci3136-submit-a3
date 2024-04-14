WeChat: cstutorcs
QQ: 749389476
Email: tutorcs@163.com

import java.util.ArrayList;

/*
 * The first step is to convert regular expressions to NFA. according to the TOY
 * language, the regular expression rule for int, float,id,symbol,reference,char
 * and string are different,so out solution is to create different NFA for these
 * rules.
 * 
 * Step1, copy the regular expression from TOY Lexical Structure at
 * https://web.cs.dal.ca/~nzeh/Teaching/3136/Assignments/toylang.html.
 * 
 * Step2, convert each regular expression to one NFA, for example,
 * nfa_int_matcher is an integer NFA, if one word is provided to this matcher,
 * we can know if this word is an integer.
 * 
 */
public class TokenTypeFinder {

	private static NFAMatcher nfa_int_matcher;
	private static NFAMatcher nfa_float_matcher;
	private static NFAMatcher nfa_id_matcher;
	private static NFAMatcher nfa_symbol_matcher;
	private static NFAMatcher nfa_reference_matcher;
	private static NFAMatcher nfa_char_matcher;
	private static NFAMatcher nfa_string_matcher;

	private static void init_nfa_matchers() {
		try {
			ArrayList<String> regext_toy_set = new ArrayList<String>();
			regext_toy_set.add("[+-]?[0-9]+");
			regext_toy_set.add("|");
			regext_toy_set.add("0x[0-9a-fA-F]+");
			regext_toy_set.add("|");
			regext_toy_set.add("0b[01]+");
			nfa_int_matcher = new NFAMatcher(regext_toy_set);

			// start to float
			regext_toy_set.clear();
			regext_toy_set.add("[+-]?([0-9]*\\.)?[0-9]+([Ee][+-]?[0-9]+)?");
			nfa_float_matcher = new NFAMatcher(regext_toy_set);

			// id, according to teacher's email, modifying to the new rule.
			regext_toy_set.clear();
			// regext_toy_set.add("[^0-9:%].*");
			regext_toy_set.add("[^'0-9:%].*");
			nfa_id_matcher = new NFAMatcher(regext_toy_set);

			// for symbol
			regext_toy_set.clear();
			regext_toy_set.add(":.+");
			nfa_symbol_matcher = new NFAMatcher(regext_toy_set);

			// reference
			regext_toy_set.clear();
			regext_toy_set.add("%[^0-9:%].*");
			nfa_reference_matcher = new NFAMatcher(regext_toy_set);

			// char
			regext_toy_set.clear();
			regext_toy_set.add("'[^\']'");
			nfa_char_matcher = new NFAMatcher(regext_toy_set);

			// string
			regext_toy_set.clear();
			String s = "\\\"([^\\\"])*\\\"";
			// System.out.println(s);
			regext_toy_set.add(s);
			nfa_string_matcher = new NFAMatcher(regext_toy_set);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// for each input word, using each NFA to evaluate it, so we can know thich
	// type it is
	public static int getTokenKeyByWord(String word) {
		int ret = -1;
		boolean accepted = false;
		init_nfa_matchers();

		accepted = nfa_id_matcher.check(word);
		if (accepted) {
			ret = TokenDefinition.TOY_ID;
			return ret;
		}
		accepted = nfa_int_matcher.check(word);
		if (accepted) {
			ret = TokenDefinition.TOY_INT;
			return ret;
		}
		accepted = nfa_float_matcher.check(word);
		if (accepted) {
			ret = TokenDefinition.TOY_FLOAT;
			return ret;
		}

		accepted = nfa_symbol_matcher.check(word);
		if (accepted) {
			ret = TokenDefinition.TOY_SYMBOL;
			return ret;
		}
		accepted = nfa_reference_matcher.check(word);
		if (accepted) {
			ret = TokenDefinition.TOY_REFERENCE;
			return ret;
		}
		accepted = nfa_char_matcher.check(word);
		if (accepted) {
			ret = TokenDefinition.TOY_CHAR;
			return ret;
		}
		accepted = nfa_string_matcher.check(word);
		if (accepted) {
			ret = TokenDefinition.TOY_STRING;
			return ret;
		}
		return ret;
	}

}
