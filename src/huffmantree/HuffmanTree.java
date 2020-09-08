package huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
哈夫曼树
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int [] arr = {13,7,8,3,29,6,1};
        Node root = createHuffmanTree(arr);

        //测试
        preOrder(root);
    }

    public static void preOrder(Node root){
        if (root != null){
            root.preOrder();
        }else {
            System.out.println("空树");
        }
    }


    //创建哈夫曼树

    /**
     *
     * @param arr
     * @return
     */
    public static Node createHuffmanTree( int [] arr){
        List<Node> nodes = new ArrayList<>();
        for (int value : arr){
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1) {
            //排序  == 从小打到
            Collections.sort(nodes);
            System.out.println("node= " + nodes);

            //取出根节点权值最小的二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            //构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            //从arrayList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //加入新的节点
            nodes.add(parent);
          //  System.out.println("第一次处理" + nodes);
        }
        //返回哈夫曼树的root
        return nodes.get(0);
    }
}
//让Node 实现 Comparable接口
class Node implements Comparable<Node> {
   public int value;
    public Node left;
    public Node right;

    //前序遍历
    public  void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排序 假如从大到小 则加个负号
        return this.value - o.value;
    }
}