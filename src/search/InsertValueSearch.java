package search;

import java.util.Arrays;

public class InsertValueSearch {
    public static void main(String[] args) {
        int [] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i+1;
        }
        int i = insertValueSearch(arr, 0, arr.length - 1, 1);
        if (i != -1){
            System.out.println("i:"+i);
        }else {
            System.out.println("没找到");
        }
    }

    static int count = 0;
    //插值查找算法
    public static int insertValueSearch(int [] arr,int left,int right,int findVal){

        System.out.println("调用次数"+(++count));
        if (left > right || findVal > arr[right] || findVal < arr[left]){
            return  -1;
        }
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal){  //向右递归
             return insertValueSearch(arr,mid + 1,right,findVal);
        }else if (findVal < midVal){
            return insertValueSearch(arr,left,mid - 1,findVal);
        }else {
            return mid;
        }
    }
}
