package hashtab;

import java.util.Scanner;

/**
 * 用不带表头的链表 head直接指向Emp
 */
public class HashtabDemo {
    public static void main(String[] args) {

        HashTab hashTab = new HashTab(7);
        //简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加员工");
            System.out.println("list: 显示员工");
            System.out.println("find: 查找员工");
            System.out.println("exit:退出");
            String s = scanner.next();
            switch (s) {
                case "add":
                    System.out.println("输入员工id");
                    int id = scanner.nextInt();
                    System.out.println("输入员工姓名");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("输入查找员工id");
                    int Nid = scanner.nextInt();
                    hashTab.findEmp(Nid);
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
            }
        }
    }
}

//创建hashTab 管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size;

    public HashTab(int size) {
        //初始化链表 7条大链表
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        //大坑
        //没有初始化具体的7条链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加
    public void add(Emp emp) {
        //根据员工的id  得到该员工应当加入的链表
        int empLinkedListNO = hashFun(emp.id);
        empLinkedListArray[empLinkedListNO].add(emp);
    }

    //遍历哈希表
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list();
        }
    }

    //查找
    public void findEmp(int id){
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findEmp(id);
        if (emp != null){
            System.out.printf("在第%d条链表找到 雇员id = %d\n",empLinkedListNO+1,id);
        }else {
            System.out.println("在哈希表中，没有找到该雇员");
        }
    }

    //编写散点函数
    public int hashFun(int id) {
        return id % size;
    }
}

//雇员
class Emp {
    public int id;
    public String name;
    public Emp next;

    //构造器
    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//创建EmpLinkedList，表示链表
class EmpLinkedList {
    private Emp head;

    public void add(Emp emp) {
        //head 为空 直接添加
        if (head == null) {
            head = emp;
            return;
        }
        //head 不为空 添加到最后
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                //将emp 加入到链表最后
                break;
            }
            //继续遍历
          curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    public void list() {
        if (head == null) {
            System.out.println("当前链表为空");
            return;
        }

        Emp curEmp = head;
        while (true) {

            System.out.printf("id = %d, name = %s", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                curEmp = null; //置为空  方便结果的判断
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    public Emp findEmp(int id){
        //判断链表是否为空
        if (head == null){
            System.out.println("链表为空");
        }
        Emp curEmp = head;
        while (true){
            if (curEmp.id == id){ //找到了
                break;
            }
            if (curEmp.next == null){  //遍历全部了
                System.out.println("没找到");
                break;
            }
            curEmp = curEmp.next;
        }
        //退出循环了 就找了
        return curEmp;
    }
}

