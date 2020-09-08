package test;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        // 测试
        HashTab hashTab = new HashTab(7);
        String key = "";
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("add: 添加员工");
            System.out.println("list: 显示员工");
            System.out.println("find : 查找员工");
            System.out.println("exit: 退出程序");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("员工id");
                    int id = scanner.nextInt();
                    System.out.println("员工姓名");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("输入查找员工id");
                    int Eid = scanner.nextInt();
                    hashTab.findEmp(Eid);
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    System.out.println("程序退出");
                    break;

                // 输入非法字符 也退出
                default:
                    scanner.close();
                    loop = false;
                    System.out.println("程序退出");
                    break;
            }

        }
    }
}

// hashtab
class HashTab {
    private EmpLikedList[] empLikedListArrays;
    private int size;

    // 初始化链表
    public HashTab(int size) {
        this.size = size;
        empLikedListArrays = new EmpLikedList[size];

        for (int i = 0; i < size; i++) {
            empLikedListArrays[i] = new EmpLikedList();
        }
    }

    // hashtab add
    public void add(Emp emp) {
        // 需要一个散点函数
        int empLikedListArraysNO = HashFun(emp.id);
        empLikedListArrays[empLikedListArraysNO].add(emp);
    }

    // hashtab list
    public void list() {
        for (int i = 0; i < size; i++) {
            empLikedListArrays[i].list();
        }
    }

    // hashtab find
    public void findEmp(int id) {
        int empLikedListArraysNO = HashFun(id);
        Emp emp = empLikedListArrays[empLikedListArraysNO].findEmp(id);
        if (emp != null){
            System.out.printf("在第%d条链表找到 雇员id = %d\n",empLikedListArraysNO+1,id);
        }else {
            System.out.println("在哈希表中，没有找到该雇员");
        }
    }

    // 散点函数 取模(简单的)
    public int HashFun(int id) {
        return id % size;
    }
}

// Emp
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

}

// EmpLinkedList
class EmpLikedList {
    private Emp head;

    // 添加
    public void add(Emp emp) {
        // 判断链表是否为空 为空就直接加到head -- 采用不带头节点的单链表
        if (head == null) {
            head = emp;
            return;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) { // 遍历完成
                break;
            }
            curEmp = curEmp.next;
        }
        // 找到最后了，直接加入
        curEmp.next = emp;
    }

    // 显示
    public void list() {
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        Emp curEmp = head;
        while (true) {
          //  System.out.printf("--> id = %d ,name = %s\n", curEmp.id, curEmp.name);
            System.out.printf("id = %d, name = %s", curEmp.id, curEmp.name);

            if (curEmp.next == null) { // 遍历到链表最后了
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    // 查找
    public Emp findEmp(int id) {
        if (head == null) {
            System.out.println("链表为空");

        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                break;
            }

            if (curEmp.next == null) { // 遍历到链表最后了
                curEmp = null;
                return curEmp;
            }
            curEmp = curEmp.next;
        }
        return curEmp;

    }
}
