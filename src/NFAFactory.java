WeChat: cstutorcs
QQ: 749389476
Email: tutorcs@163.com


/*
 * This is to create NFA objects
 */
import java.util.Set;
import java.util.Stack;

public class NFAFactory {
    private RegexProvider regexProvider;
    private NFADigram[] array_nfa_list = null;
    private Stack<NFADigram> stack_nfa = null;
    private int nfa_node_index = 0;

    public NFAFactory(RegexProvider regexProvider) {
        this.regexProvider = regexProvider;
        array_nfa_list = NFAUtils.initNodes();
        stack_nfa = new Stack<NFADigram>();
        while (regexProvider.checkRegexFlag(RegexFlag.END_OF_PHRASE)) {
            regexProvider.moveWithRightStep();
        }
    }

    public void operate_expression(NFANodes nfaNode) {
        try {
            concat_expression(nfaNode);
            NFANodes temp_node = new NFANodes();

            while (regexProvider.checkRegexFlag(RegexFlag.OR)) {
                regexProvider.moveWithRightStep();
                concat_expression(temp_node);

                NFADigram startNode = addOneNode();
                startNode.next2 = temp_node.start_node;
                startNode.next = nfaNode.start_node;
                nfaNode.start_node = startNode;

                NFADigram endNode = addOneNode();
                nfaNode.final_node.next = endNode;
                temp_node.final_node.next = endNode;
                nfaNode.final_node = endNode;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void concat_expression(NFANodes node) {
        try {
            if (NFAUtils.operation1(regexProvider.getCurrentToken())) {
                startHandle(node);
            }
            char c = (char) regexProvider.getProvider();
            while (NFAUtils.operation1(regexProvider.getCurrentToken())) {
                NFANodes temp_node = new NFANodes();
                startHandle(temp_node);
                node.final_node.next = temp_node.start_node;
                node.final_node = temp_node.final_node;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean checkCLOSURE() {

        if (regexProvider.currentToken != RegexFlag.CLOSURE)
            return true;
        else
            return false;
    }

    public void startHandle(NFANodes node) throws Exception {
        token_handler(node);

        boolean operation_flag = setStart(node);
        if (operation_flag == false) {
            operation_flag = setUnion(node);
        }
        if (operation_flag == false) {
            operation_flag = setConcat(node);
        }

    }

    public boolean setStart(NFANodes node) {
        NFADigram start, end;
        try {
            if (checkCLOSURE()) {
                return false;
            }
            start = addOneNode();
            end = addOneNode();
            NFAUtils.setStart(regexProvider, node, start, end);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean checkPLUS_CLOSE() {

        if (regexProvider.currentToken != RegexFlag.PLUS_CLOSE)
            return true;
        else
            return false;
    }

    public boolean setUnion(NFANodes node) {
        NFADigram start, end;
        try {
            if (checkPLUS_CLOSE()) {
                return false;
            }
            start = addOneNode();
            end = addOneNode();
            NFAUtils.setUnion(regexProvider, node, start, end);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean checkOPEN() {

        if (regexProvider.currentToken != RegexFlag.OPEN_PAREN)
            return true;
        else
            return false;
    }

    private boolean checkCLOSE() {

        if (regexProvider.currentToken != RegexFlag.CLOSE_PAREN)
            return true;
        else
            return false;
    }

    public boolean setConcat(NFANodes node) {
        NFADigram start, end;
        try {
            if (regexProvider.checkRegexFlag(RegexFlag.OPTIONAL) == false) {
                return false;
            }

            start = addOneNode();
            end = addOneNode();

            NFAUtils.setConcat(regexProvider, node, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void token_handler(NFANodes node) {
        try {
            boolean handled = buildChildNode(node);
            if (!handled) {
                handled = buildNode1(node);
            }

            if (!handled) {
                handled = buildNode2(node);
            }

            if (!handled) {
                buildNode3(node);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean buildChildNode(NFANodes node) throws Exception {
        if (!checkOPEN()) {
            regexProvider.moveWithRightStep();
            operate_expression(node);
            if (!checkCLOSE()) {
                regexProvider.moveWithRightStep();
            } else {
                throw new Exception("fatal errors!");
            }

            return true;
        }

        return false;
    }

    private boolean checkDEFAULT() {

        if (regexProvider.currentToken != RegexFlag.DEFAULT_CHAR)
            return true;
        else
            return false;
    }

    public boolean buildNode1(NFANodes node) throws Exception {
        if (checkDEFAULT()) {
            return false;
        }

        NFADigram start = null;
        start = node.start_node = addOneNode();
        node.final_node = node.start_node.next = addOneNode();

        start.setEdge(regexProvider.getProvider());

        regexProvider.moveWithRightStep();

        return true;
    }

    public boolean buildNode2(NFANodes node) throws Exception {
        if (checkANY()) {
            return false;
        }

        NFADigram start = null;
        start = node.start_node = addOneNode();
        node.final_node = node.start_node.next = addOneNode();

        start.setEdge(TOYConstants.CCL);
        start.addToSet((byte) '\n');
        start.addToSet((byte) '\r');
        start.setComplement();

        regexProvider.moveWithRightStep();

        return true;
    }

    private boolean checkANY() {

        if (regexProvider.currentToken != RegexFlag.ANY)
            return true;
        else
            return false;
    }

    private boolean checkCCLStart() {

        if (regexProvider.currentToken != RegexFlag.CCL_START)
            return true;
        else
            return false;
    }

    private boolean checkCCLEND() {

        if (regexProvider.currentToken != RegexFlag.CCL_END)
            return true;
        else
            return false;
    }

    private boolean checkBOL() {

        if (regexProvider.currentToken != RegexFlag.AT_BOL)
            return true;
        else
            return false;
    }

    public boolean buildNode3(NFANodes node) throws Exception {
        if (checkCCLStart()) {
            return false;
        }

        regexProvider.moveWithRightStep();
        boolean negative = false;
        if (!checkBOL()) {
            negative = true;
        }

        NFADigram start = null;
        start = node.start_node = addOneNode();
        node.final_node = node.start_node.next = addOneNode();
        start.setEdge(TOYConstants.CCL);

        if (checkCCLEND()) {
            buildNode4(start.nfaContainer);
        }

        if (checkCCLEND()) {
            throw new Exception("fatal errors!");
        }

        if (negative) {
            start.setComplement();
        }

        regexProvider.moveWithRightStep();

        return true;
    }

    private void buildNode4(Set<Byte> set) {
        int first = 0;

        while (regexProvider.currentToken != RegexFlag.END_OF_PHRASE && regexProvider.currentToken != RegexFlag.CCL_END) {

            if (regexProvider.currentToken != RegexFlag.DASH) {
                first = regexProvider.getProvider();
                set.add((byte) first);
            } else {
                regexProvider.moveWithRightStep();
                for (; first <= regexProvider.getProvider(); first++) {
                    set.add((byte) first);
                }
            }
            regexProvider.moveWithRightStep();
        }
    }

    public NFADigram addOneNode() throws Exception {

        NFADigram nfa = null;
        if (stack_nfa.size() > 0) {
            nfa = stack_nfa.pop();
        } else {
            nfa = array_nfa_list[nfa_node_index];
            nfa_node_index++;
        }

        nfa.clear();
        nfa.setIndexOfState(0);
        nfa.setEdge(TOYConstants.EPSILON);

        return nfa;
    }

}
