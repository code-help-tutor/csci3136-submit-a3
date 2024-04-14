WeChat: cstutorcs
QQ: 749389476
Email: tutorcs@163.com


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class NFADigram {
    private int edge;
    public Set<Byte> nfaContainer;
    public NFADigram next;
    public NFADigram next2;
    private NFAElementSet element_set;
    private int index_of_stat;

    public NFADigram() {
        nfaContainer = new HashSet<Byte>();
        clear();
    }

    public void setIndexOfState(int index_of_stat) {
        this.index_of_stat = index_of_stat;
    }

    public int getIndexOfStat() {
        return index_of_stat;
    }

    public void addToSet(Byte b) {
        nfaContainer.add(b);
    }

    public void setComplement() {
        Set<Byte> newSet = new HashSet<Byte>();

        for (byte b = 0; b < 127; b++) {
            if (nfaContainer.contains(b) == false) {
                newSet.add(b);
            }
        }
        nfaContainer = null;
        nfaContainer = newSet;
    }

    public void setElementSet(NFAElementSet anchor) {
        this.element_set = anchor;
    }

    public NFAElementSet getElementSet() {
        return this.element_set;
    }

    public void clear() {
        index_of_stat = -1;
        next = null;
        nfaContainer.clear();
        next2 = null;
        element_set = NFAElementSet.NONE;
    }

    public void copyInstance(NFADigram sourceNode) {
        nfaContainer.clear();
        for (Byte b : sourceNode.nfaContainer) {
            nfaContainer.add(b);
        }
        element_set = sourceNode.getElementSet();
        this.next = sourceNode.next;
        this.next2 = sourceNode.next2;
        this.edge = sourceNode.getEdge();
    }

    public int getEdge() {
        return edge;
    }

    public void setEdge(int edge) {
        this.edge = edge;
    }
}
