package sparsearray;

import java.io.*;

/**
 * 稀疏数组
 * 操作： 数组 -->稀疏数组 -->磁盘 -->数组
 */
public class SparseArray {
    public static void main(String[] args) throws IOException {
        // 创建一个原始的二维数组 11 * 11
        // 0: 表示没有棋子， 1 表示 黑子 2 表蓝子

        int[][] chessArry1 = new int[11][11];
        chessArry1[1][2] = 1;
        chessArry1[2][3] = 2;
        chessArry1[4][5] = 2;
        // 输出原始的二维数组
        for (int[] row : chessArry1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 将二维数组 转 稀疏数组的思
        // 1. 先遍历二维数组 得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArry1[i][j] != 0) {
                    sum++;
                }
            }
        }

        // 2. 创建对应的稀疏数组
        int[][] sparseArray = new int[sum + 1][3];
        // 给稀疏数组赋值
        sparseArray[0][0] = chessArry1.length;
        sparseArray[0][1] = chessArry1.length;
        sparseArray[0][2] = sum;


        // 遍历二维数组，将非0的值存放到 sparseArr中
        int count = 0;
        for (int i = 0; i < chessArry1.length; i++) {
            for (int j = 0; j < chessArry1.length; j++) {
                if (chessArry1[i][j] != 0) {
                    count++;  //行数的控制
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArry1[i][j];
                }
            }
        }
        // 输出稀疏数组的形式
        System.out.println();
        System.out.println("得到稀疏数组为~~~~");
//        for (int i = 0; i < sparseArray.length; i++) {
//            System.out.printf("%d\t%d\t%d\t\n", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
//        }

        for (int [] row: sparseArray) {
            for (int data:row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        System.out.println();

        //文件路径
        String str = "f:/sparse.txt";
        File file = new File(str);
        //如果磁盘中不存在该文件，则创建该文件
        if (file != null) {
            file.createNewFile();
        }
        //定义高效缓冲字符流
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (int i = 0; i < sparseArray.length; i++) {
            for (int j = 0; j < sparseArray[i].length; j++) {
                bufferedWriter.write(sparseArray[i][j]);
            }

        }
        //关闭文件流
        bufferedWriter.close();
        fileWriter.close();
        System.out.println("sparseArray 写入磁盘成功");


        //创建新的数组，来接收数据
        int[][] sparseArray2 = new int[sum+1][3];
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        for (int i = 0; i < sparseArray2.length; i++) {
            for (int j = 0; j < sparseArray2[i].length; j++) {
                sparseArray2[i][j] = bufferedReader.read();
            }
        }
        //关闭文件流
        bufferedReader.close();
        fileReader.close();

        System.out.println("输出从文件中读取到的稀疏数组");

        for (int i = 0; i < sparseArray2.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArray2[i][0], sparseArray2[i][1], sparseArray2[i][2]);
        }
        System.out.println();

        //恢复二位数组

        //将稀疏数组 --》 恢复成 原始的二维数组
		/*
		 *  1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的  chessArr2 = int [11][11]
			2. 在读取稀疏数组后几行的数据，并赋给 原始的二维数组 即可.
		 */

        //1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组

        int[][] chessArray2 = new int[sparseArray2[0][0]][sparseArray2[0][1]];

        // 输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组");

        //2.在读取稀疏数组后几行的数据，并赋给 原始的二维数组 即可.
        for (int i = 1; i < sparseArray2.length; i++) {
            chessArray2 [sparseArray2[i][0]] [sparseArray2[i][1]] =sparseArray2[i][2];
        }

        for (int [] row:chessArray2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
