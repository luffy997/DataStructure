package stack;

public class Calculator {
    public static void main(String[] args) {
        //根据前面老师的思路，完成表达式的运算
        String expression = "30+2*6-2";
        //创建两个栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描得到的char保存在ch
        String keepNum = "";
        //开始循环的扫描expression
        while (true){
            //依次得到expression 的每一个字符
            ch = expression.substring(index,index+1).charAt(0);
            //判断ch是什么，然后做相应处理
            if (operStack.isOper(ch)){  //如果是运算符
                //判断符号栈是否为空
                if (!operStack.isEmpty()){
                    //如果符号栈有操作符，就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符,就需要从数栈中pop出两个数,
                    //在从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈
                    if (operStack.priority(ch)<= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 =numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        numStack.push(res);
                        operStack.push(ch);
                    }else {
                        //如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈
                        operStack.push(ch);
                    }
                }else {
                    //如果为空，直接入符号栈
                    operStack.push(ch);
                }
            }else {  //如果是数，就直接入数栈
                //numStack.push(ch - 48);
                //1.当处理多位数时，不能发现一个数就立即入栈，可能是多位数
                //2.在处理数，需要
                //3.定义字符串，拼接
                keepNum += ch;

                //如果ch 已经是expression的最后以为，就直接入栈
                if (index == expression.length() -1){
                     numStack.push(Integer.parseInt(keepNum));
                }else {
                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，就入栈
                    //只是看下最后一位，不能index++
                    if (operStack.isOper(expression.substring(index+1,index+2).charAt(0))) {
                        //如果后一位是运算符，则入栈 keepNum = "1" 或者 "123"
                       numStack.push(Integer.parseInt(keepNum));
                        //重要的!!!!!!, keepNum清空
                        keepNum = "";

                    }
                }
            }
            //index + 1,并判断是否扫描到expression最后
            index ++;
            if (index >= expression.length()){
                break;
            }
        }
        //当扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运算
        while (true){
            //如果当前栈为空，则计算到最后的结果，数栈中只有一个数字【结果】
            if (operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 =numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        System.out.printf("表达式%s = %d",expression,numStack.pop());
    }
}


//创建一个栈
//定义一个类，表示栈结构
class  ArrayStack2{
    private int maxSize; //栈的大小
    private int [] stack;
    private int top = -1; //栈顶，初始化-1

    //构造器
    public ArrayStack2(int maxSize){
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

    //返回栈顶数据
    public  int peek(){
        return stack[top];
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

    //返回运算符的优先级，程序员来确定，优先级使用数字表示
    //数字越大，优先级越高
    public  int priority(int oper){
        if (oper == '*' || oper == '/'){
            return 1;
        }else if (oper == '+' || oper == '-'){
            return  0;
        }else {
            return -1; //目前表达式只有 四则运算
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/' ;
    }

    //计算方法
    public int cal(int num1, int num2,int oper){
        int res = 0; //存放计算的结果
        switch (oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
        }
        return  res;
    }
}
