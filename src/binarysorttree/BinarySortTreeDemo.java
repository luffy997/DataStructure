package binarysorttree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {

        int [] arr = {7,3,10,12,5,1,9,2};
        BinaryTree binaryTree = new BinaryTree();
        for (int i = 0; i < arr.length; i++) {
            binaryTree.add(new Node(arr[i]));
        }

        System.out.println("中序遍历");
        binaryTree.infixOrder();

        //测试删除叶子节点
        binaryTree.delNode(2);
        binaryTree.delNode(5);
        binaryTree.delNode(9);
        binaryTree.delNode(12);
        binaryTree.delNode(7);
        binaryTree.delNode(3);
        binaryTree.delNode(10);
//        binaryTree.delNode(1);
        System.out.println("删除节点后");
        System.out.println("根节点"+binaryTree.getRoot());

        binaryTree.infixOrder();
    }
}

//创建二叉树
class BinaryTree{
    public Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void add(Node node){
        if (this.root == null){
            this.root = node;
        }else {
            this.root.add(node);
        }
    }

    //
    public int delRightTreeMin(Node node){
        Node target = node;
        while (target.left != null){
            target = target.left;
        }
        delNode(target.value);
        return  target.value;
    }

    //查找节点
    public Node search(int value){
        if (root == null){
            return null;
        }else {
            return this.root.search(value);
        }
    }

    //查找当前删除节点的父节点
    public Node searchParent(int value){
        if (root == null){
            return null;
        }else {
            return this.root.searchParent(value);
        }
    }

    //删除节点
    public void delNode(int value){
        if (root ==  null){
            return;
        }else {
            //1.需要找到需要删除的节点
            Node targetNode = search(value);
            //如果没有找到要删除的节点
            if (targetNode == null){
                return;
            }
            //如果我们发现当前这颗二叉树只有一个节点
            if (root.left == null && root.right == null){
                root = null;
                return;
            }
            //去找当前节点的父节点
            Node parentNode = searchParent(value);
            //如果要删除的节点是叶子节点
            if (targetNode.left == null && targetNode.right == null){
              //判断targetNode 是父节点的左子节点还是右子节点
                if (parentNode.left != null && parentNode.left.value == value){
                        parentNode.left = null;
                }else if (parentNode.right != null && parentNode.right.value == value){
                        parentNode.right = null;
                }
            }else if (targetNode.left != null && targetNode.right != null){
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;

            }else { //删除只有一颗子树的节点
                //如果要删除的节点有左子节点
                if (targetNode.left != null){
                    if (parentNode != null){
                        //如果 tagetNode 是 parent 的左子节点
                        if (parentNode.left.value == value){
                            parentNode.left = targetNode.left;
                        }else { //tagetNode 是parent 的左子节点
                            parentNode.right = targetNode.left;
                        }
                    }else {
                        root = targetNode.left;
                    }


                }else { //如果要删除的节点有右子节点
                    if (parentNode != null){
                        //如果targetNode 是parent的左子节点
                        if (parentNode.left.value == value){
                            parentNode.left = targetNode.right;
                        }else { //如果targetNode 是parent的右子节点
                            parentNode.right = targetNode.right;
                        }
                    }else {
                     root = targetNode.right;
                    }

                }
            }
        }
    }

    //中序遍历
    public void infixOrder(){
        if (this.root == null){
            System.out.println("树为空了，无法遍历");
            return;
        }
        this.root.infixOrder();
    }
}


//创建节点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //查找节点
    public Node search(int value){
        if (this.value == value){
            return this;
        }else if (value < this.value){
            if (this.left == null){
                return  null;
            }
                return this.left.search(value);

        }else {
                if (this.right == null){
                    return null;
                }
                    return this.right.search(value);
            }
    }

    //查找父节点
    /**
     *
     * @param value 要找到的节点的值
     * @return 返回删除节点的父节点，没有就返回null
     */
    public Node searchParent(int value){
        //当前节点就是要删除节点的父节点，就直接返回
        if ((this.left != null && this.left.value == value)   ||  (this.right != null && this.right.value == value)){
            return this;
        }else {
            if (value < this.value && this.left != null){
                return this.left.searchParent(value);
            }else if (value >= this.value && this.right != null){
                return this.right.searchParent(value);
            }else {
                return null;
            }
        }
    }


    //添加node的方法
    public void add(Node node) {
        if (node.value < this.value) { //小于根节点
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        }else {
            if (this.right == null){
                this.right = node;
            }else {
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder(){
        if (this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.infixOrder();
        }
    }
}
