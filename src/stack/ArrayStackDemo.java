package stack;

import java.util.Scanner;
//使用数组模拟栈
public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试
        ArrayStack stack = new ArrayStack(4);
        String key = " ";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop){
            System.out.println("show:表示显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:退出程序");
            System.out.println("pop:退出程序");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try{
                        int res = stack.pop();
                        System.out.printf("出栈的数据%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

//定义一个类，表示栈结构
class  ArrayStack{
    private int maxSize; //栈的大小
    private int [] stack;
    private int top = -1; //栈顶，初始化-1

    //构造器
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //判断栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //判断栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }
    //入栈
    public void push(int value){
        //判断栈是否满
        if (isFull()){
            System.out.println("栈已满，不能进行入栈操作");
            return;
        }
        top ++;
        stack[top] = value;
    }

    //出栈
    public int pop(){
        //判断栈是否为空
        if (isEmpty()){
            throw  new RuntimeException("栈为空，不能进行出栈操作");
        }
        int value = stack[top];
        top -- ;
        return value;
    }

    //显示栈  从top开始
    public  void  list(){
        if (isEmpty()){
            System.out.println("栈为空");
            return;
        }
        for (int i = top;i >=0;i --){
            System.out.printf("栈stack[%d] = %d\n",i,stack[top]);
        }
    }
}
