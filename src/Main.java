

public class Main {
    public static void main(String[] args) {
        System.out.println("Входные данные: (9 (12 (14 (19) (23)))(12(17(67)(54))(72(76)(50))))");
        NodeForAVL binTree = MyPars.parse("(9 (12 (14 (19) (23)))(12(17(67)(54))(72(76)(50))))");
        System.out.println("Исходное дерево:\n"+binTree);
        MyAVLTree myAvlTree = new MyAVLTree(binTree);
        System.out.println("АВЛ Дерево:\n"+ myAvlTree);
        System.out.println("");

    }
}


