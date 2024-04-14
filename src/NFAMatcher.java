WeChat: cstutorcs
QQ: 749389476
Email: tutorcs@163.com


/**
 * Step1, create NFA from one regular expression; Step2, provide one check
 * method to evaluate one word using created NFA
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class NFAMatcher {
	private NFADigram start;

	public NFAMatcher(ArrayList<String> regext_toy_set) {
		RegexProvider regexProvider = new RegexProvider(regext_toy_set);
		NFAFactory nfaFactory = new NFAFactory(regexProvider);
		NFANodes nfaNodes = new NFANodes();
		nfaFactory.operate_expression(nfaNodes);
		this.start = nfaNodes.start_node;
	}

	/*
	 * main idea is to evaluate one word by the followings logic:
	 * 
	 * if the final closure is in the subset, it means this NFA accept this word
	 **/
	public boolean check(String word) {
		boolean ret = false;
		HashSet<NFADigram> next = new HashSet<NFADigram>();
		next.add(start);
		next = NFAUtils.calculate_subset(next);

		HashSet<NFADigram> current = null;
		char c;
		boolean final_checked = false;

		char[] array_regex = word.toCharArray();
		for (int i = 0; i < array_regex.length; i++) {
			c = array_regex[i];
			current = NFAUtils.move(next, c);
			next = NFAUtils.calculate_subset(current);
			if (next != null && !next.isEmpty()) {
				boolean found = false;
				Iterator<NFADigram> ver = next.iterator();
				while (ver.hasNext()) {
					NFADigram p = (NFADigram) ver.next();
					if (p.next == null && p.next2 == null) {
						found = true;
					}
				}
				if (found) {
					final_checked = true;
				}
			} else {
				break;
			}
		}
		if (final_checked) {
			ret = true;
		}
		return ret;
	}
}
