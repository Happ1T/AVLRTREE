public class MyAVLTree {

    private NodeForAVL root;

    MyAVLTree(NodeForAVL root) {
        if (root == null) {
            throw new NullPointerException();
        } else {
            addAll(root);
        }
    }

    public void addAll(NodeForAVL root) {
        if (root != null) {
            add(root.value);
            if (root.left != null) addAll(root.left);
            if (root.right != null) addAll(root.right);
        } else {
            throw new NullPointerException();
        }
    }

    public void add(int value) {
        if (root == null) {
            root = new NodeForAVL(value);
        } else if (!find(value)) {

            if (root.value > value) {
                root.left = add(value, root.left);

            } else {
                root.right = add(value, root.right);

            }
        }
        root = balance(root);

    }

    private NodeForAVL add(int value, NodeForAVL tree) {
        if (tree == null) {
            tree = new NodeForAVL(value);

        } else if (tree.value > value) {
            tree.left = add(value, tree.left);
        } else {
            tree.right = add(value, tree.right);
        }
        return tree;

    }

    public void remove(int value) {
        if(root==null){
            return;
        }else if(root.value==value){
            NodeForAVL newNode = root;
            root = null;
            addAll(newNode.right);
            addAll(newNode.left);
        }else{
            NodeForAVL newNode = findParent(value);
            if(newNode==null){
                return;
            }else if(newNode.left.value==value){
                NodeForAVL tmp = newNode.left;
                newNode.left = null;
                addAll(tmp.left);
                addAll(tmp.right);
            }else {
                NodeForAVL tmp = newNode.right;
                newNode.right = null;
                addAll(tmp.left);
                addAll(tmp.right);
            }
        }
    }
    private NodeForAVL findParent(int value){
        return findParent(value,root);
    }
    private NodeForAVL findParent(int value, NodeForAVL tree){
        if(tree==null){
            return tree;
        }else if(tree.value == value){
            return tree;
        }else if(tree.value> value){
            if(tree.left==null){
                return null;
            }else if(tree.left.value == value){
                return tree;
            }else return findParent(value,tree.left);
        }else {
            if(tree.right==null){
                return null;
            }else if(tree.right.value == value){
                return tree;
            }else return findParent(value,tree.right);
        }
    }

    private boolean isLeaf(NodeForAVL tree){
        return tree!=null&&tree.left==null&&tree.right==null?true:false;
    }

    private NodeForAVL balance(NodeForAVL tree) {
        if (tree == null) return tree;
        else {
            tree.left = balance(tree.left);
            tree.right = balance(tree.right);

            if (isNeedToBalance(tree)) {
                int maxDepthRR;
                int maxDepthRL;
                int maxDepthLR;
                int maxDepthLL;
                if (tree.right != null) {
                    maxDepthRL = maxDepth(tree.right.left);
                    maxDepthRR = maxDepth(tree.right.right);
                    if (maxDepthRL <= maxDepthRR) {
                        tree = minLeftRotation(tree);
                    } else if (maxDepthRL > maxDepthRR) {
                        tree = bigLeftRotation(tree);
                    }
                } else if (tree.left != null) {
                    maxDepthLR = maxDepth(tree.left.right);
                    maxDepthLL = maxDepth(tree.left.left);
                    if (maxDepthLR <= maxDepthLL) {
                        tree = minRightRotation(tree);
                    } else if (maxDepthLR > maxDepthLL) {
                        tree = bigRightRotation(tree);
                    }
                }


            }

            return tree;
        }
    }

    private NodeForAVL minLeftRotation(NodeForAVL tree) {
        if (tree != null) {
            NodeForAVL newNode = tree.right;
            tree.right = newNode.left;
            newNode.left = tree;
            return newNode;
        }
        return tree;
    }

    private NodeForAVL minRightRotation(NodeForAVL tree) {
        if (tree != null) {
            NodeForAVL newNode = tree.left;
            tree.left = newNode.right;
            newNode.right = tree;
            return newNode;
        }
        return tree;
    }

    private NodeForAVL bigLeftRotation(NodeForAVL tree) {
        tree.right = minRightRotation(tree.right);
        tree = minLeftRotation(tree);
        return tree;
    }

    private NodeForAVL bigRightRotation(NodeForAVL tree) {
        tree.left = minLeftRotation(tree.left);
        tree = minRightRotation(tree);
        return tree;
    }

    private boolean isNeedToBalance(NodeForAVL root) {
        if (root != null) {
            if (Math.abs(maxDepth(root.left) - maxDepth(root.right)) > 1) return true;
            else return false;
        } else return false;
    }

    private int maxDepth(NodeForAVL tree) {
        if (tree == null) {
            return 0;
        } else {
            return Math.max(maxDepth(tree.left), maxDepth(tree.right)) + 1;
        }
    }


    public boolean find(int value) {
        return find(value, root);
    }

    private boolean find(int value, NodeForAVL tree) {
        if (tree != null) {
            if (tree.value == value) return true;
            else if (tree.value > value) return find(value, tree.left);
            else return find(value, tree.right);
        } else return false;
    }

    public String toString() {
        if (root != null) {
            return root.toString();
        }
        return "";
    }


}
