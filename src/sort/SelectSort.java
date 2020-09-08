package sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
       // int[] arr = {101, 34, 119, 1};
        long begin =System.currentTimeMillis();
        int[] arr =  new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random()*800000); //生成 0~800000
        }
        System.out.println("排序前");
        //System.out.println(Arrays.toString(arr));
        selectSort(arr);
        System.out.println("排序后");
        //System.out.println(Arrays.toString(arr));
        long end =System.currentTimeMillis();
        System.out.println("耗费时间"+(double)(end-begin)/1000); //2.222S
    }

    //第一轮
    public static void selectSort(int[] arr) {
        int minIndex = 0;
        int min = 0;
        for (int j = 0; j < arr.length - 1; j++) {
           minIndex = j;
           min = arr[j];
            for (int i = j + 1; i < arr.length; i++) {
                if (min > arr[i]) { //说明假定的最小值，并不是最小
                    min = arr[i]; //重置min
                    minIndex = i; //重置minIndex
                }
            }
            //将最小值，放在arr[0] 即交换
            if (minIndex != j) {
                arr[minIndex] = arr[j];
                arr[j] = min;
            }
        }
    }
}
