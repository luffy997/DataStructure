package search;

import java.util.ArrayList;
import java.util.List;

//二分查找
//必须是有序的数组
public class BinarySearch {
    public static void main(String[] args) {
        int [] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        int  index = binarySearch(arr,0,arr.length -1,1);
        if (index != -1){
            System.out.println("index="+index);
        }else {
            System.out.println("没找到");
        }
       // List<Integer> resIndexList = binarySearch2(arr, 0, arr.length -1, 1234);
       //System.out.println("resIndexList:"+resIndexList);
    }

    /**
     *
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    static int count = 0;
    public static  int binarySearch(int [] arr,int left,int right,int findVal){
        System.out.println("递归次数"+(count++));
        //当left > right ，说明递归整个数组，但没找到    因为最后一步是 left = right
        //如果后面再递归 就会造成堆栈溢出
        if (left > right){
            return -1;
        }
        int mid  = (left+right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal){  //向右递归
            return binarySearch(arr,mid+1,right,findVal);
        }else if (findVal < midVal){ //向左递归
            return binarySearch(arr,left,mid-1,findVal);
        }else {
            return mid;
        }
    }

    //升级版 拿到查找数值的全部索引 放入集合中
    public static ArrayList<Integer> binarySearch2(int [] arr, int left, int right, int findVal){

        //当left > right ，说明递归整个数组，但没找到    因为最后一步是 left = right
        //如果后面再递归 就会造成堆栈溢出
        if (left > right){
            return new ArrayList<Integer>();
        }
        int mid  = (left+right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal){  //向右递归
            return binarySearch2(arr,mid+1,right,findVal);
        }else if (findVal < midVal){ //向左递归
            return binarySearch2(arr,left,mid-1,findVal);
        }else {
            ArrayList<Integer> resIndexList = new ArrayList<Integer>();

            //向左扫描
            int temp = mid -1;
            while (true){
                if (temp < 0 || arr[temp] != findVal){
                    break;
                }
                //否则，就将temp 放入到resIndexList
                resIndexList.add(temp);
                temp -= 1;
            }
            resIndexList.add(mid);

            //向右扫描
            temp = mid +1;
            while (true){
                if (temp > arr.length -1 || arr[temp] !=findVal ){
                    break;
                }
                resIndexList.add(temp);
                temp +=1;
            }
            return resIndexList;
        }
    }
}
