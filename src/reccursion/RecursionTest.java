package reccursion;
//递归测试
public class RecursionTest {
    public static void main(String[] args) {
        test(4);
//        int res = factorial(4);
//        System.out.println("res="+res);
    }

    //打印
    public static void test(int n){
        if (n > 2){
            test(n- 1);
        }
        System.out.println("n="+n);
    }

    //阶乘
    public static int factorial(int n){
        if (n == 1){
            return 1;
        }else {
            return factorial(n-1)*n;
        }
    }
}
