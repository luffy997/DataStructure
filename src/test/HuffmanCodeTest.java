package test;

import java.util.*;


public class HuffmanCodeTest {
    public static void main(String[] args) {
        String str = "faster,highter,stronger";
        byte[] strBytes = str.getBytes();
        System.out.println(Arrays.toString(strBytes));

        //创建list<Node>
        List<Node> node = getNode(strBytes);
        System.out.println("node==" + node);

        //根据List<Node> 创建哈夫曼树
        Node huffmanTreeRoot = createHuffmanTree(node);
        System.out.println("先序遍历");
        //  System.out.println("huffmanTreeRoot"+huffmanTreeRoot);
        //huffmanTreeRoot.preOrder();
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        System.out.println("生成的哈夫曼编码"+huffmanCodes);
        byte[] huffmanCodeBytes = zip(strBytes, huffmanCodes);
        System.out.println("huffmanCodeBytes"+Arrays.toString(huffmanCodeBytes)+"  "+"length"+huffmanCodeBytes.length);

        //解码
        byte[] sourceByte = decode(huffmanCodes, huffmanCodeBytes);
        System.out.println("解码后的字符串= "+new String(sourceByte));
    }

    //完成数据的解码
    //思路
    //1.将huffman数组[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //重写成二进制字符串“101010000101111.。。”
    //2.哈夫曼编码对应的二进制字符串“101010000101111.。。” => 对照 赫夫曼编码 => "i like like like java do you like a java"

    public static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes){
        //先得到huffmanBytes 对应的二进制字符串，形式10101010000101111
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length -1);
            stringBuilder.append(byteToBitString(!flag,b));
        }
        //把字符串按照指定的赫夫曼编码进行解码
        //把赫夫曼编码进行调换，因为反向查询 a - >100
        HashMap<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte,String> entry : huffmanCodes.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }

        //创建一个集合，存放byte
        ArrayList<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1; //定义一个小的计算器
            boolean flag= true;
            Byte b = null;

            while (flag){
                //递增的取出key
                String key = stringBuilder.substring(i, i + count); //i 不动，让count移动，指定匹配到下一个字符
                b = map.get(key);
                if (b ==  null){ //说明没有匹配到
                    count ++;
                }else {
                    flag = false;
                }
            }
            list.add(b);
            i += count; //直接将i移动到count
        }
        //当佛如、循环结束后，list中就存放了所有字符
        //把list中的数据放入到byte[] 返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }
    /**
     * 将byte 转成一个二进制字符串
     * @param b 传入的byte
     * @param  flag 标志是否需要补高位，如果是true，表示需要补高位，如果是false表示不补
     * @return
     */
    public static String byteToBitString(boolean flag,byte b){
        //用变量保存b
        int temp = b;
        //如果正数，我们需要补高位
        if (flag){
            temp |= 256; //
        }
        String str = Integer.toBinaryString(temp); //返回的是temp 对应二进制的补码
        if (flag){
            return  str.substring(str.length() - 8);
        }else {
            return  str;
        }
    }

    //编写一个方法，将字符串对应的byte[] 数组，通过哈夫曼编码表，返回一个哈夫曼编码压缩后的字符数组
    /**
     * @param bytes
     * @param huffmanCodes
     */
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //1.利用huffmanCodes 将 bytes 转成 哈夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes 数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //System.out.println("测试stringBuilder="+stringBuilder);

        //将“10101000101111110.。。”转成byte[]
        //统计返回 byte[] huffmanCodeBytes 长度
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    //为了方便，重写getCodes
    public static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        getCode(root.left, "0", stringBuilder);
        getCode(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    //生成哈夫曼树对应的哈夫曼编码
    //1.将哈夫曼编码表存放在Map<Byte,String> 形式
    //2.生成的哈夫曼编码表

    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 功能：将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合
     *
     * @param node          传入结点
     * @param code          路径： 左子结点是 0, 右子结点 1
     * @param stringBuilder 用于拼接路径
     */
    public static void getCode(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null) {
            if (node.data == null) { //判断该节点是叶子节点还是非叶子节点
                //非叶子节点，则往下找
                //向左递归 向右递归
                getCode(node.left, "0", stringBuilder2);
                getCode(node.right, "1", stringBuilder2);

            } else {
                //叶子节点
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }

    }


    //创建node
    public static List<Node> getNode(byte[] strBytes) {
        ArrayList<Node> nodes = new ArrayList<>();
        HashMap<Byte, Integer> hashMap = new HashMap<>();
        //hashmap={97=1, 114=1, 115=1, 116=1, 101=1, 102=1, 103=1, 104=1, 105=1, 44=1, 110=1, 111=1}
        for (byte b : strBytes) {
            Integer count = hashMap.get(b);
            if (count == null) {
                hashMap.put(b, 1);
            } else {
                hashMap.put(b, count + 1);
            }
        }

        //遍历Map
        for (Map.Entry<Byte, Integer> entry : hashMap.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //根据list<Node> 创建哈夫曼树
    public static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes); //从小到大排
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            //挂上左右节点
            parent.left = leftNode;
            parent.right = rightNode;

            //删除之前用过的
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            nodes.add(parent);
        }
        //到最后 只有一个了  就返回
        return nodes.get(0);
    }

    //前序遍历
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("哈夫曼树为空");
        }
    }

}

class Node implements Comparable<Node> {
    Byte data;
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
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
}
