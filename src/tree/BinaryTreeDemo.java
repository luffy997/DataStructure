package tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        //测试
        //创建一棵二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node1 = new HeroNode(2, "吴用");
        HeroNode node2 = new HeroNode(3, "卢俊义");
        HeroNode node3 = new HeroNode(4, "林冲");
        HeroNode node4 = new HeroNode(5, "关胜");
        //手动生成二叉树
        binaryTree.setRoot(root);
        root.left = node1;
        root.right = node2;
        node2.right = node3;
        node2.left = node4;
//        System.out.println("先序遍历"); //12354
//        binaryTree.preOrder();
//        System.out.println("中序遍历");  //21534
//        binaryTree.midOrder();
//        System.out.println("后序遍历");  //25431
//        binaryTree.postOrder();
//        System.out.println("先序遍历查找：");
//        HeroNode resNode = binaryTree.preOrderSearch(2);
//        if (resNode != null) {
//            System.out.printf("找到了，no = %d name  = %s \n", resNode.no, resNode.name);
//        } else {
//            System.out.println("没有找到");
//        }
//
//        System.out.println("中序遍历查找：");
//        resNode = binaryTree.midOrderSearch(5);
//        if (resNode != null) {
//            System.out.printf("找到了，no = %d name  = %s \n", resNode.no, resNode.name);
//        } else {
//            System.out.println("没有找到");
//        }
//
//        System.out.println("后序遍历查找：");
//        resNode = binaryTree.postOrderSearch(5);
//        if (resNode != null) {
//            System.out.printf("找到了，no = %d name  = %s \n", resNode.no, resNode.name);
//        } else {
//            System.out.println("没有找到");
//        }
        System.out.println("先序遍历"); //12354
        binaryTree.preOrder();
        System.out.println("删除节点"); //12354
        binaryTree.delNode(5);
       // binaryTree.delNode(3);
        binaryTree.preOrder();
    }
}

//定义BinaryTree
class BinaryTree {
    public HeroNode root; //根节点

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //先序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    //中序遍历
    public void midOrder() {
        if (this.root != null) {
            this.root.midOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    //先序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return this.root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序遍历查找
    public HeroNode midOrderSearch(int no) {
        if (root != null) {
            return this.root.midOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return this.root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    //删除节点
    public void delNode(int no){
        if (this.root != null) {
            if (this.root.no == no) {
                this.root = null;
            } else {
                //递归删除
                root.delNode(no);
            }
        }else {
            System.out.println("二叉树为空");
        }
    }
}


//定义HeroNode节点
class HeroNode {
    public int no;
    public String name;
    public HeroNode left;
    public HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void midOrder() {
        if (this.left != null) {
            this.left.midOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.midOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //先序遍历查找
    public HeroNode preOrderSearch(int no) {
        //首先判断当前节点是不是要找的
        System.out.println("进入先序遍历查找：");
        if (this.no == no) {
            return this;
        }
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
            if (resNode != null) {
                return resNode;
            }
        }

        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    public HeroNode midOrderSearch(int no) {

        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.midOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入中序遍历查找：");
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.midOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        System.out.println("进入后序遍历查找：");  //写在比较节点的前面
        if (this.no == no) {
            return this;
        }
        return resNode;
    }

    //递归删除节点
    //1. 如果删除的节点是叶子节点，则删除该节点
    //2. 如果删除的节点是非叶子节点，则删除该子树

    public void delNode(int no){
        //判断当前节点是不是需要删除节点
        if (this.left !=null && this.left.no== no){
            this.left = null;
            return;
        }
        if (this.right !=null && this.right.no== no){
            this.right = null;
            return;
        }

        //向左子树递归删除
        if (this.left != null){
            this.left.delNode(no);
        }
        //向右子树递归删除
        if (this.right != null){
            this.right.delNode(no);
        }
    }

}


