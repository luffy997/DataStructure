package queue;

import java.util.Scanner;


/**
 * 用数组实现环形链表
 * 很重要的思路： 空一个位置  预留位 方便操作
 * 求队列的长度 (rear+maxSize-front)%maxSize
 * 对于入队和出队 操作也是需要取模运算的
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //测试
        System.out.println("环形队列的操作：");
        CricleArray arrayQueue = new CricleArray(4);
        char key = ' ' ;  //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get)：出队");
            System.out.println("h(head)：查看队列头部");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数字");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头部数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
            }
        }
        System.out.println("程序退出");

    }
}

class  CricleArray{
    private int maxSize; // 表示数组的最大容量
    //front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
    //front 的初始值 = 0
    private int front;
    //rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
    //rear 的初始值 = 0
    private int rear; // 队列尾
    private int[] arr; // 该数据用于存放数据, 模拟队列

    public CricleArray(int maxSize){
        this.maxSize = maxSize;
        front = 0;
        rear = 0;
        arr =  new int[maxSize];
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return front == rear;
    }

    //判断队列是否已满
    public boolean isFull(){
        return (rear+1)%maxSize == front;
    }

    //入队
    public  void addQueue(int n){
        //判断是否已满
        if (isFull()){
            System.out.println("队列已满，不能进行入队操作");
            return;
        }
        //直接加入数据
        arr[rear]=n;
        //将rear后移，这里必须取模
        rear=(rear + 1)%maxSize;
    }

    //出队
    public  int getQueue(){
        //判断是否为空
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        //这里需要分析出front是指向队列的第一个元素
        //1.先把front对应的值保留到一个临时变量
        //2.将front后移,考虑取模
        //3.将临时保存的变量返回
        int value=arr[front];
        front=(front+1)%maxSize;
        return value;
    }

    //显示队列所有数据
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空");
            return;
        }
        //思路： 从front开始遍历，遍历多少元素
        for (int i = front; i< size()+front;i++){
            System.out.printf("arr[%d]=%d\n",i % maxSize,arr[i % maxSize]);
        }
    }

    //求出队列有效数据个数
    public int size(){
        return (rear+maxSize-front)%maxSize;
    }

    //获取头数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[front];
    }

}