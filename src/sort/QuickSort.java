package sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        // int [] arr = {-9,78,0,23,-567,70,1000,-1,90000};
        //  System.out.println("arr="+ Arrays.toString(arr));

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 10000); //生成 0~800000
        }
        System.out.println("排序前");
        long begin = System.currentTimeMillis();
        //System.out.println(Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        //System.out.println("排序后");
        //System.out.println(Arrays.toString(arr));
        long end = System.currentTimeMillis();
        System.out.println("耗费时间" + (double) (end - begin) / 1000); //0.051S
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        //中间值 pivot
        int pivot = arr[(left + right) / 2];
        int temp = 0;
        //while循环的目的是让比pivot值小的放到左边
        //比pivot值大的放到右边
        while (l < r) {
            //在pivot的左边一直找，找到大于等于pivot的值才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            //在pivot的右边一直找，找到小于等于于pivot的值才退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            //如果 l >= 2 说明pivot的左右两边的值，已经按照左边全部是
            //小于等于pivot，右边是全部大于pivot
            if (l >= r) {
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后，发现这个arr[l] == pivot 值 相等r--  前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            //如果交换完后，发现这个arr[r] == pivot值  相等l++，后移
            if (arr[r] == pivot) {
                l += 1;
            }
        }
        //如果l == r ,必须l++，r--，否则出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
