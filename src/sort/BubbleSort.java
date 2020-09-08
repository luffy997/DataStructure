package sort;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int arr[] = {3, 9, -1, 10, 20};
//        //int arr[] = {1,2,3,4,5};
//        System.out.println("排序前：");
//        System.out.println(Arrays.toString(arr));
        
        //测试冒泡排序的速度 80000个数据
        long begin =System.currentTimeMillis();
        int[] arr =  new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random()*800000); //生成 0，800000

        }
        bubbleSort(arr);
        long end =System.currentTimeMillis();
        System.out.println("耗费时间"+(double)(end-begin)/1000); //11.26S

    }

    public static void bubbleSort(int[] arr) {
        //时间复杂度 O(n^2)
        int temp = 0;
        boolean flag = false; //表示变量，表示是否进行过交换 --优化 当一趟排序  一次也没交换 就直接break
        for (int j = 0; j < arr.length - 1; j++) {
            for (int i = 0; i < arr.length - 1 - j; i++) {
                if (arr[i] > arr[i + 1]) {
                    flag = true;
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
            //  System.out.printf("第%d次排序结果",j+1);
            //System.out.println(Arrays.toString(arr));
            if (!flag) { //在一趟排序中，依次都没交换
                break;
            } else {
                flag = false; //重置flag，进行下次判断
            }
        }
    }
}

