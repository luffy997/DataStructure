package sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        // int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        long begin = System.currentTimeMillis();
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 800000); //生成 0~800000
        }
        System.out.println("排序前");
        //System.out.println(Arrays.toString(arr));
        shellSort2(arr);
        System.out.println("排序后");
        //System.out.println(Arrays.toString(arr));
        long end = System.currentTimeMillis();
        System.out.println("耗费时间" + (double) (end - begin) / 1000 + "S"); //6.389S
    }

    //使用逐步推导的方式来编写希尔排序
    //希尔排序时，对有序序列在插入时采用交换法
    public static void shellSort(int[] arr) {
        int temp = 0;
        int count = 0;
        //根据逐步分析，使用循环处理
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {

            //第一轮，10个数据分成5组
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有的元素（共gap组，每组个元素），步长gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前元素大于加上步长后的那个元素，进行交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            // System.out.println("希尔排序第" + (++count) + "轮=" + Arrays.toString(arr));
        }

    }

    //对交换式的希尔排序进行优化 -> 移位法
    public static void shellSort2(int[] arr) {

        //
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素，逐个对其所在的组进行插入排序
            for (int i = gap; i < arr.length ; i++){
                int j =i ;
                int temp = arr[j];
                if (arr[j] < arr[j-gap]){
                    while (j -gap >= 0 && temp < arr[j - gap]){
                        //移动
                        arr[j] = arr[j-gap];
                        j -= gap;
                    }
                    //当退出while，就给temp找到插入的位置
                    arr [j] = temp;
                }
            }
        }
       // System.out.println(Arrays.toString(arr));
    }
}
