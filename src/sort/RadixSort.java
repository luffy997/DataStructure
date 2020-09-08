package sort;

import java.util.Arrays;
//负数就不要用基数排序
public class RadixSort {
    public static void main(String[] args) {
       int[] arr = {53, 3, 542, 748, 14, 214};
       // int arr[] = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 10000); //生成 0~800000
        }
        System.out.println("排序前");
        long begin = System.currentTimeMillis();
        //System.out.println(Arrays.toString(arr));
        radixSort(arr);
        System.out.println("排序后");
        //System.out.println(Arrays.toString(arr));
        long end = System.currentTimeMillis();
        System.out.println("耗费时间" + (double) (end - begin) / 1000); //0.027S

    }

    public static void radixSort(int[] arr) {

        //根据前面的推导过程，我们可以得到最终的基数排序代码
        //1.得到最大的位数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxLength = (max + " ").length();  //转字符串 去length 得到长度


        //定义一个二维数组，表示10个桶, 每个桶就是一个一维数组
        //说明
        //1. 二维数组包含10个一维数组
        //2. 为了防止在放入数的时候，数据溢出，则每个一维数组(桶)，大小定为arr.length
        //3. 名明确，基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        //循环处理
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            int[] bucketElementCounts = new int[10];
            //第n轮
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的个位的值
                int digitOfElement = arr[j] / n % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来数组）
            int index = 0;
            //遍历每一桶，并将桶中数据，放入原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据，我们放入到原数组
                if (bucketElementCounts[k] != 0) {
                    //循环该桶即第K个桶（即第k个一维数组），放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr
                        arr[index++] = bucket[k][l];
                    }
                }
                bucketElementCounts[k] = 0;
            }
           // System.out.println("第" + (i + 1) + "轮基数排序后：" + Arrays.toString(arr));
        }
    }
}
/**
 //为了记录每个桶中，实际存放了多少个数据,我们定义一个一维数组来记录各个桶的每次放入的数据个数
 //可以这里理解
 //比如：bucketElementCounts[0] , 记录的就是  bucket[0] 桶的放入数据个数
 int[] bucketElementCounts = new int[10];
 //第一轮
 for (int j = 0; j < arr.length; j++) {
 //取出每个元素的个位的值
 int digitOfElement = arr[j] / 1 % 10;
 //放入到对应的桶中
 bucket[digitOfElement] [bucketElementCounts[digitOfElement]] = arr[j];
 bucketElementCounts[digitOfElement] ++;
 }
 //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来数组）
 int index = 0;
 //遍历每一桶，并将桶中数据，放入原数组
 for (int k = 0; k < bucketElementCounts.length; k++) {
 //如果桶中有数据，我们放入到原数组
 if (bucketElementCounts[k] !=0){
 //循环该桶即第K个桶（即第k个一维数组），放入
 for (int l = 0;l < bucketElementCounts[k]; l++){
 //取出元素放入到arr
 arr[index++] = bucket[k][l];
 }
 }
 bucketElementCounts[k] = 0;
 }
 System.out.println("第一轮基数排序后："+ Arrays.toString(arr));

 //第二轮
 for (int j = 0; j < arr.length; j++) {
 //取出每个元素的个位的值
 int digitOfElement = arr[j] / 10 % 10;
 //放入到对应的桶中
 bucket[digitOfElement] [bucketElementCounts[digitOfElement]] = arr[j];
 bucketElementCounts[digitOfElement] ++;
 }
 //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来数组）
 index = 0;
 //遍历每一桶，并将桶中数据，放入原数组
 for (int k = 0; k < bucketElementCounts.length; k++) {
 //如果桶中有数据，我们放入到原数组
 if (bucketElementCounts[k] !=0){
 //循环该桶即第K个桶（即第k个一维数组），放入
 for (int l = 0;l < bucketElementCounts[k]; l++){
 //取出元素放入到arr
 arr[index++] = bucket[k][l];
 }
 }
 bucketElementCounts[k] = 0;
 }
 System.out.println("第二轮基数排序后："+ Arrays.toString(arr));

 //第三轮
 for (int j = 0; j < arr.length; j++) {
 //取出每个元素的个位的值
 int digitOfElement = arr[j] / 100 % 10;
 //放入到对应的桶中
 bucket[digitOfElement] [bucketElementCounts[digitOfElement]] = arr[j];
 bucketElementCounts[digitOfElement] ++;
 }
 //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来数组）
 index = 0;
 //遍历每一桶，并将桶中数据，放入原数组
 for (int k = 0; k < bucketElementCounts.length; k++) {
 //如果桶中有数据，我们放入到原数组
 if (bucketElementCounts[k] !=0){
 //循环该桶即第K个桶（即第k个一维数组），放入
 for (int l = 0;l < bucketElementCounts[k]; l++){
 //取出元素放入到arr
 arr[index++] = bucket[k][l];
 }
 }
 }
 System.out.println("第三轮基数排序后："+ Arrays.toString(arr));

 }
 */


