package huffmancode;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
       // String destFile = "F:\\DataStructuresZipDemo\\yuba1582800668834.jpg";
//        String destFile = "F:\\DataStructuresZipDemo\\springmvc.xml";
//        String zipFile = "F:\\DataStructuresZipDemo\\springmvc.zip";
//        String srcFile = "F:\\DataStructuresZipDemo\\springmvc.xml";

       // zipFile(srcFile, destFile);
      //  unZipFile(zipFile,destFile);
       // System.out.println("操作成功");
        String content = "stronger";

        byte[] contentBytes  = content.getBytes();
        System.out.println("未压缩"+new String(contentBytes)+"length=="+contentBytes.length);
        byte[] huffmanCoodeBytes = huffmanZip(contentBytes);
        System.out.println("压缩后的结果"+Arrays.toString(huffmanCoodeBytes)+"长度==="+huffmanCoodeBytes.length);

        byte[] sourceByte = decode(huffmanCodes, huffmanCoodeBytes);
        System.out.println("解码后的字符串= "+new String(sourceByte));
        // System.out.println(Arrays.toString(contentBytes));
        //    System.out.println(contentBytes.length);
//
//        List<Node> nodes = getNodes(contentBytes);
//        System.out.println(nodes);

        //创建二叉树
        //       Node huffmanTreeRoot = createHuffmanTree(nodes);
        //     System.out.println("前序遍历");
        //    huffmanTreeRoot.preOrder();

        //     Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //      System.out.println("生成的哈夫曼编码"+huffmanCodes);
//
//        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
        //       System.out.println("huffmanCodeBytes"+Arrays.toString(huffmanCodeBytes)+"  "+"length"+huffmanCodeBytes.length);
    }

    //编写一个方法，将一个文件进行解压

    /**
     * @param zipFile  待解压文件内
     * @param destFile 解压文件
     */
    public static void unZipFile(String zipFile, String destFile) {
        //创建输出流
        FileOutputStream outputStream = null;
        //创建输入流
        FileInputStream inputStream = null;
        //创建对象输出流
        ObjectInputStream objectInputStream = null;

        try {
            inputStream = new FileInputStream(zipFile);
            objectInputStream = new ObjectInputStream(inputStream);
            byte[] huffmanBytes = (byte[]) objectInputStream.readObject();
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) objectInputStream.readObject();

            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //把bytes写入目标文件
            outputStream = new FileOutputStream(destFile);
            outputStream.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
         try {
             objectInputStream.close();
             inputStream.close();
             outputStream.close();
         }catch (Exception e){
             System.out.println(e.getMessage());
         }
        }
    }

    //编写方法，将一个文件进行压缩
    public static void zipFile(String srFile, String destFile) {

        //创建输出流
        FileOutputStream outputStream = null;
        //创建输入流
        FileInputStream inputStream = null;
        //创建对象输出流
        ObjectOutputStream objectOutputStream = null;

        try {
            inputStream = new FileInputStream(srFile);

            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            byte[] huffmanCodeBytes = huffmanZip(b);

            outputStream = new FileOutputStream(destFile);
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(huffmanCodeBytes);
            objectOutputStream.writeObject(huffmanCodes);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                inputStream.close();
                objectOutputStream.close();
                outputStream.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }


    //完成数据的解码
    //思路
    //1.将huffman数组[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //重写成二进制字符串“101010000101111.。。”
    //2.哈夫曼编码对应的二进制字符串“101010000101111.。。” => 对照 赫夫曼编码 => "i like like like java do you like a java"

    public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //先得到huffmanBytes 对应的二进制字符串，形式10101010000101111
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        //把字符串按照指定的赫夫曼编码进行解码
        //把赫夫曼编码进行调换，因为反向查询 a - >100
        HashMap<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        //创建一个集合，存放byte
        ArrayList<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1; //定义一个小的计算器
            boolean flag = true;
            Byte b = null;

            while (flag) {
                //递增的取出key
                String key = stringBuilder.substring(i, i + count); //i 不动，让count移动，指定匹配到下一个字符
                b = map.get(key);
                if (b == null) { //说明没有匹配到
                    count++;
                } else {
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
     *
     * @param b    传入的byte
     * @param flag 标志是否需要补高位，如果是true，表示需要补高位，如果是false表示不补
     * @return
     */
    public static String byteToBitString(boolean flag, byte b) {
        //用变量保存b
        int temp = b;
        //如果正数，我们需要补高位
        if (flag) {
            temp |= 256; //
        }
        String str = Integer.toBinaryString(temp); //返回的是temp 对应二进制的补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    //使用一个方法，将前面的方法封装起来，便于调用
    public static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        //根据nodes 创建哈夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //对应的哈夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        System.out.println("huffmancodes==" + huffmanCodes.size());
        //根据生成的哈夫曼编码，压缩到压缩后的哈夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;

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

    public static void getCode(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code加入stringBuilder2
        stringBuilder2.append(code);
        if (node != null) { //如果node == null  不处理
            //判断node是叶子节点还是非叶子节点
            if (node.data == null) { //非叶子节点
                //递归
                //向左递归
                getCode(node.left, "0", stringBuilder2);
                getCode(node.right, "1", stringBuilder2);
            } else {
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    //前序遍历
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("树为空");
        }
    }

    public static List<Node> getNodes(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList<>();

        //遍历bytes，统计 每一个byte出现的次数
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }

        //把每一个键值对转成一个node对象，加入到node集合
        //遍历map
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //通过list 创建哈夫曼树
    public static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //从小大大
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            //把左右节点挂在树上
            parent.left = leftNode;
            parent.right = rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //把parent加入nodes中
            nodes.add(parent);
        }
        //nodes 最后的节点 就是根节点

        return nodes.get(0);
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

    //先序遍历
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