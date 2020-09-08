package sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
       // int[] arr = {101, 34, 119, 1};
        long begin =System.currentTimeMillis();
        int[] arr =  new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random()*10000); //生成 0~800000
        }
        System.out.println("排序前");
       // System.out.println(Arrays.toString(arr));
        inserrSort(arr);
        System.out.println("排序后");
        //System.out.println(Arrays.toString(arr));
        long end =System.currentTimeMillis();
        System.out.println("耗费时间"+(double)(end-begin)/1000); //0.668S
    }

    public static void inserrSort(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {  //从大到小排序 < 改 > 即可
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            if (insertIndex +1 != i){
                arr[insertIndex + 1] = insertVal;
            }
            //System.out.println("第一轮");
           // System.out.println();
           // System.out.println(Arrays.toString(arr));
        }
    }
}
