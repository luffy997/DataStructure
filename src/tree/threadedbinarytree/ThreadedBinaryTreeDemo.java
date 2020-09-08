package tree.threadedbinarytree;

/*
线索二叉树
遍历线索化二叉树
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");
        //二叉树，后面递归创建
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;

        //测试线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNode();

        //测试  10号节点做测试
        HeroNode left = node5.left;
        HeroNode right = node5.right;
        System.out.println("10号节点的前驱" + left); //3
        System.out.println("10号节点的后继" + right); //1

        System.out.println("使用线索化的方式遍历线索化二叉树");
        threadedBinaryTree.threadedList();
    }
}

//定义BinaryTree
class ThreadedBinaryTree {
    public HeroNode root; //根节点

    //为了实现线索化，需要创建要给指向当前节点的前驱节点的指针
    public HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //重载
    public void threadedNode() {
        this.threadedNode(root);
    }

    //遍历线索化二叉树的方法
    public void threadedList() {
        //定义一个变量，存储当前遍历的节点，从root开始
        HeroNode node = root;
        while (node != null) {
            //循环的找到leftType == 1 的节点，第一个找到的就是8
            //后面随着遍历而变化，因为当leftType == 1 时，说明该节点是按照线索化的
            //处理后的有效节点
            while (node.leftType == 0) {
                node = node.left;
            }

            //打印当前节点
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点，就一直输出
            while (node.rightType == 1) {
                node = node.right;
                System.out.println(node);
            }
            //替换这个遍历的节点
            node = node.right;
        }
    }

    //线索化二叉树
    public void threadedNode(HeroNode node) {

        //如果node为空，不能线索化
        if (node == null) {
            return;
        }
        //1.线索化左子树
        threadedNode(node.left);

        //2.线索化当前节点
        //先处理当前节点的前驱节点
        if (node.left == null) {
            //让当前节点的左指针
            node.left = pre;
            node.leftType = 1;
        }
        //处理后继节点 --> 用下一次来处理  因为是单向的 不能在一次中
        // 同时处理两个事务
        if (pre != null && pre.right == null) {
            pre.right = node;
            pre.rightType = 1;
        }
        //每处理一个节点后，让当前节点是下一个节点的前驱节点
        //!!! 很重要
        pre = node;

        //3.线索化右子树
        threadedNode(node.right);
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
    public void delNode(int no) {
        if (this.root != null) {
            if (this.root.no == no) {
                this.root = null;
            } else {
                //递归删除
                root.delNode(no);
            }
        } else {
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

    //1.如果leftType == 0 表示指向的是左子树，如果1 则表示指向的前驱节点
    //2.如果rightType == 0 表示指向的是右子树，如果1则表示指向后继节点
    public int leftType;
    public int rightType;

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

    public void delNode(int no) {
        //判断当前节点是不是需要删除节点
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }

        //向左子树递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        //向右子树递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

}