WeChat: cstutorcs
QQ: 749389476
Email: tutorcs@163.com


/*
 * This class is designed to provide some common operations with NFA
 */
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class NFAUtils {

	public static NFADigram[] initNodes() {
		NFADigram[] array_nfa_nodes = new NFADigram[TOYConstants.DEFAULT_SIZE];
		for (int i = 0; i < TOYConstants.DEFAULT_SIZE; i++) {
			array_nfa_nodes[i] = new NFADigram();
		}
		return array_nfa_nodes;
	}

	public static HashSet<NFADigram> move(HashSet<NFADigram> input, char c) {
		HashSet<NFADigram> outSet = new HashSet<NFADigram>();
		Iterator it = input.iterator();

		while (it.hasNext()) {
			NFADigram p = (NFADigram) it.next();

			int stateNum = p.getIndexOfStat();
			Set<Byte> s = p.nfaContainer;
			Byte cb = (byte) c;
			boolean b = s.contains(cb);
			boolean ccl = p.getEdge() == TOYConstants.CCL;

			if (p.getEdge() == c
					|| (p.getEdge() == TOYConstants.CCL && p.nfaContainer.contains(cb))) {
				NFADigram next = p.next;

				outSet.add(p.next);
			}
		}

		return outSet;
	}

	public static HashSet<NFADigram> calculate_subset(HashSet<NFADigram> input) {

		Stack<NFADigram> nfaStack = new Stack<NFADigram>();
		if (input == null || input.isEmpty()) {
			return null;
		}

		Iterator<NFADigram> it = input.iterator();
		while (it.hasNext()) {
			nfaStack.add(it.next());
		}

		while (nfaStack.empty() == false) {
			NFADigram p = nfaStack.pop();

			if (p.next != null && p.getEdge() == TOYConstants.EPSILON) {
				if (input.contains(p.next) == false) {
					NFADigram next = p.next;

					nfaStack.push(p.next);
					input.add(p.next);
				}
			}

			if (p.next2 != null && p.getEdge() == TOYConstants.EPSILON) {
				if (input.contains(p.next2) == false) {
					NFADigram next = p.next2;

					nfaStack.push(p.next2);
					input.add(p.next2);
				}
			}
		}

		return input;
	}

	public static boolean setStart(RegexProvider regexProvider, NFANodes node,
			NFADigram start, NFADigram end) {

		try {
			start.next = node.start_node;
			node.final_node.next = node.start_node;

			start.next2 = end;
			node.final_node.next2 = end;

			node.start_node = start;
			node.final_node = end;

			regexProvider.moveWithRightStep();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public static boolean setUnion(RegexProvider regexProvider, NFANodes node,
			NFADigram start, NFADigram end) {

		try {

			start.next = node.start_node;
			node.final_node.next2 = end;
			node.final_node.next = node.start_node;

			node.start_node = start;
			node.final_node = end;

			regexProvider.moveWithRightStep();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public static boolean setConcat(RegexProvider regexProvider, NFANodes node,
			NFADigram start, NFADigram end) {

		try {

			start.next = node.start_node;
			node.final_node.next = end;

			start.next2 = end;

			node.start_node = start;
			node.final_node = end;

			regexProvider.moveWithRightStep();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public static boolean operation1(RegexFlag tok) {
		boolean ret = true;
		if (tok == RegexFlag.END_OF_PHRASE || tok == RegexFlag.CLOSE_PAREN)
			ret = false;
		return ret;
	}

}
