import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Trie {

    private TrieNode root;

    public void addWord(String word) {
        if (root == null) {
            root = new TrieNode(' ');
        }
        TrieNode start = root;
        char[] charecters = word.toCharArray();
        for (char c : charecters) {
            if (start.getChilds().size() == 0) {
                TrieNode newNode = new TrieNode(c);
                start.getChilds().add(newNode);
                start = newNode;
            } else {
                ListIterator iterator = start.getChilds().listIterator();
                TrieNode node = null;
                while (iterator.hasNext()) {
                    node = (TrieNode) iterator.next();
                    if (node.getCharacter() >= c)
                        break;
                }
                if (node.getCharacter() == c) {
                    start = node;
                } else {
                    TrieNode newNode = new TrieNode(c);
                    iterator.add(newNode);
                    start = newNode;

                }
            }
        }

    }

    public List search(String pref) {
        if (pref == null || pref.isEmpty())
            return null;

        char[] chars = pref.toCharArray();
        TrieNode start = root;
        boolean flag = false;
        for (char c : chars) {
            if (start.getChilds().size() > 0) {
                for (TrieNode node : start.getChilds()) {
                    if (node.getCharacter() == c) {
                        start = node;
                        flag = true;
                        break;
                    }
                }
            } else {
                flag = false;
                break;
            }
        }
        if (flag) {
            List matches = getAllWords(start, pref);
            return matches;
        }

        return null;
    }

    private List getAllWords(TrieNode start, final String pf) {
        if (start.getChilds().size() == 0) {
            List lst = new LinkedList();
            lst.add(pf);
            return lst;
        } else {
            List lst = new LinkedList();
            for (TrieNode node : start.getChilds()) {
                lst.addAll(getAllWords(node, pf + node.getCharacter()));
            }
            return lst;
        }

    }

}