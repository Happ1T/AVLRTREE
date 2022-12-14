public class NodeForAVL {
    int value;
    NodeForAVL right;
    NodeForAVL left;

    NodeForAVL(int value) {
        this.value = value;
    }
    public void add(NodeForAVL r){

    }

    public String toString() {
        if (this != null) {
            return value + " " + recursiveToString(left, "│  ") + recursiveToString(right, "│  ");
        } else return "";
    }

    private String recursiveToString(NodeForAVL n, String o) {
        if (n != null) {
            return "\n" + o + "└──" + n.value + (n.left != null ? recursiveToString(n.left, o + "│  ") : "") + (n.right != null ? recursiveToString(n.right, o + "│  ") : "");
        } else {
            return "";
        }
    }



}
