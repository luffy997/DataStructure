package avl;

public class AVLTreeDemo {
    public static void main(String[] args) {
        //int [] arr = {4,3,6,5,7,8};
//        int [] arr = {10,12,8,9,7,6};
        int [] arr = {10,11,7,6,8,9};
        //创建一个AVLTree对象
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr [i]));
        }
        System.out.println("中序遍历");
        avlTree.infixOrder();

        System.out.println("在没有做平衡处理之前");
        System.out.println("树的高度"+avlTree.getRoot().height());
        System.out.println("树的左子树高度"+avlTree.getRoot().leftHeight()); //1
        System.out.println("树的右子树高度"+avlTree.getRoot().rightHeight()); //3
        System.out.println("当前节点的根节点="+avlTree.getRoot());//8
        System.out.println("根节点的左子节点="+avlTree.getRoot().right.right);//11
    }
}


//创建二叉树
class AVLTree{
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

    //返回左子树的高度
    public int leftHeight(){
        if (left == null){
            return 0;
        }else {
            return left.height();
        }
    }

    public int rightHeight(){
        if (right == null){
            return 0;
        }else {
            return right.height();
        }
    }


    //返回当前节点的搞得，以该节点为根节点的树的高度
    public int height(){
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //左旋转方法

    public void leftRotate(){
        //创建新的节点，以当前根节点的值
        Node newNode = new Node(value);
        //把新的节点的左子树设置成当前节点的左子树
        newNode.left = left;
        //把新的节点的右子树设置成当前节点的右子树的左子树
        newNode.right = right.left;
        //把当前节点的值 替换成右子节点的值
        value = right.value;
        //把当前节点的右子树设置成右子树的右子树
        right = right.right;
        //把当前节点左子树（左子节点） 设置成新的节点
        left = newNode;

    }

    //右旋转
    public void rightRotate(){
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
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

        //当添加完一个节点后，如果：（右子树的高度 -  左子树的高度） > 1 ，左旋转
        if (rightHeight() - leftHeight() > 1){
            if (right != null && right.leftHeight() > right.rightHeight()){
                //先对右子节点进行右旋转
                right.rightRotate();
                //再进行左旋转
                leftRotate();
            }else {
                leftRotate();
            }
            return;  //必须要 只能做一个处理
        }
        if (leftHeight() - rightHeight() > 1){
            if (left != null && left.rightHeight() > left.leftHeight()){
                //现对当前节点的左节点进行左旋转
                left.leftRotate();
                //再对当前节点进行右旋转
                rightRotate();
            }else {
                //直接进行右旋转即可
                rightRotate();
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
