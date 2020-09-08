package tree;

/**
 * 1.顺序二叉树通常只考虑完全二叉树
 * 2.第n个元素的左子节点位2*n+1
 * 3.第n个元素的右子节点位2*n+2
 * 4.第n个元素的父节点位(n-1)/2
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int [] arr = {1,2,3,4,5,6,7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder();
        System.out.println();
        arrayBinaryTree.midOrder();
        System.out.println();
        arrayBinaryTree.postOrder();
    }
}

class ArrayBinaryTree{
    private int [] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }
    //重载preOrder midOrder postOrder  传入0，就不用在调用的时候传入0
    public void preOrder(){
        this.preOrder(0);
    }
    public void midOrder(){
        this.midOrder(0);
    }
    public void postOrder(){
        this.postOrder(0);
    }



    //前序遍历
    public void preOrder(int index){
        //如果数组为空 或者 arr.length = 0
        if (arr == null || arr.length ==0 ){
            System.out.println("数组为空，无法遍历");
        }
        System.out.print(arr[index]+" ");
        //向左递归
        if ((index *2 +1) < arr.length){  //防止越界
            preOrder((index *2 +1));
        }
        //向右边递归
        if ((index *2 +2) < arr.length){  //防止越界
            preOrder((index *2 +2));
        }
    }

    //中序遍历
    public void midOrder(int index){
        if (arr == null || arr.length ==0 ){
            System.out.println("数组为空，无法遍历");
        }
        if ((index *2 +1) < arr.length){  //防止越界
            midOrder((index *2 +1));
        }
        System.out.print(arr[index]+" ");

        if ((index *2 +2) < arr.length){
            midOrder((index *2 +2));
        }
    }

    //后序遍历
    public void postOrder(int index){
        if (arr == null || arr.length ==0 ){
            System.out.println("数组为空，无法遍历");
        }
        if ((index * 2 +1) < arr.length){
            postOrder(index * 2 +1);
        }
        if ((index * 2 +2) < arr.length){
            postOrder(index * 2 +2);
        }
        System.out.print(arr[index]+" ");
    }
}
