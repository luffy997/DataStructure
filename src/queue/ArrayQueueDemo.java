package queue;

import java.util.Scanner;
//数组实现队列Queue
//缺陷：  没有做成一个环形队列 造成了空间的浪费
public class ArrayQueueDemo {
    public static void main(String[] args) {

        ArrayQueue arrayQueue = new ArrayQueue(3);
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
                        int res = arrayQueue.headQueen();
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

//使用数组模拟队列 --编写一个ArrayQueue
class ArrayQueue {
    //定义属性
    private int maxSize; //队列最大容量
    private int front; //队列头部 的前一个数据
    private int rear;  //队列尾部  队列最后的一个数据
    private int[] arr;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        front = -1;
        rear = -1;
        arr = new int[maxSize];  //初始化队列
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    //判断队列是否已满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //将数据添加到队列
    public void addQueue(int n) {
        //判断是否为空
        if (isFull()) {
            System.out.println("队列为空，不能添加数据");
            return;
        }
        rear++;
        arr[rear] = n;
    }

    //出队
    public int getQueue() {
        //判断是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能出队");
        }
        front++;
        return arr[front];
    }

    //显示队列所有数据
    public void showQueue() {
        if (isEmpty()){
            System.out.println("队列为空");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

    //显示队列的头
    public int headQueen(){
        //判断
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[front+1];
    }
}
