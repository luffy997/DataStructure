package tree;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
       // int [] arr ={4,6,8,5,9};
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 10000); //生成 0~800000
        }
        System.out.println("排序前");
        long begin = System.currentTimeMillis();
       // System.out.println(Arrays.toString(arr));
        headSort(arr);
        //System.out.println("排序后");
       // System.out.println(Arrays.toString(arr));
        long end = System.currentTimeMillis();
        System.out.println("耗费时间" + (double) (end - begin) / 1000); //0.014S
        headSort(arr);
    }

    public static void headSort(int arr []){
      //  System.out.println("堆排序");
        int temp = 0;
//        //分步完成
//        adjustHeap(arr,1,arr.length);
//        System.out.println("第一次"+ Arrays.toString(arr));
//        adjustHeap(arr,0,arr.length);
//        System.out.println("第二次"+ Arrays.toString(arr));

        //将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        for (int i = arr.length /2 -1;i >=0;i --){
            adjustHeap(arr,i,arr.length);
        }

        /**
         * 2.将堆顶元素与末尾元素交换，将最大元素沉到数组末端
         * 3.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行
         */
        for (int j = arr.length -1; j > 0; j--){
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,j);
        }
       // System.out.println("数组="+Arrays.toString(arr));
    }
    /**
     *
     * @param arr 待调整的数组
     * @param i  表示非叶子节点在数组中的索引
     * @param length 表示对多少个元素进行调整，length是在逐渐减少
     */
    public static void adjustHeap(int arr [],int i,int length){
        int temp =arr[i]; //先取出当前元素的值，保存在临时变量
        //开始调整
        //说明
        //1，k = i *2 +1 k是i节点的左子节点
        for (int k = i * 2 +1; k < length; k =  i * 2 +1) {
            if (k+1 < length && arr[k] < arr [k+1]){  //说明左子节点的值小于右子节点的值
                k ++;
            }
            if (arr [k] > temp){ //如果子节点大于父节点
                arr[i] = arr [k]; //把较大的值赋给当前节点
                i = k;
            }else {
                break;
            }
        }
        //当for 循环结束后，我们已经将以i 为父节点的树的最大值，放在了最顶（局部）
        arr [i] = temp; //将temp值放到调整后的位置
    }
}
