package search;
//线性查找
public class SeqSearch {
    public static void main(String[] args) {

        int [] arr ={3,12,15,9,26};
        int index =seqSearch(arr,102);
        if (index != -1){
            System.out.println("找到了，下标是："+index);
        }else {
            System.out.println("没找到");
        }
    }

    public  static int seqSearch(int [] arr,int vaule){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == vaule){
                return i;
            }
        }
        return -1;
    }
}
