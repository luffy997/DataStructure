package linkedlist;

import java.util.Stack;

//单链表
public class SingleLinkedListDemo {
    public static void main(String[] args) {

        //测试
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");


        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(heroNode1);
//        singleLinkedList.add(heroNode2);
//        singleLinkedList.add(heroNode3);
//        singleLinkedList.add(heroNode4);

        //加入按照编号的顺序
        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode4);
        singleLinkedList.addByOrder(heroNode2);
        singleLinkedList.addByOrder(heroNode3);

        singleLinkedList.list();

//        HeroNode newHeroNode = new HeroNode(2, "卢员外", "玉麒麟-");
//        System.out.println("修改后的单链表");
//        singleLinkedList.update(newHeroNode);
//        singleLinkedList.list();
//        System.out.println("删除4号节点");
//        singleLinkedList.delete(4);
//        singleLinkedList.list();

//        System.out.println("有效节点个数=" + getLength(singleLinkedList.getHead()));

//        System.out.println("倒数第1个=" + findLastIndexNode(singleLinkedList.getHead(), 1));
//
//        System.out.println("链表反转后的结果：");
//        reversetList(singleLinkedList.getHead());
//        singleLinkedList.list();
        System.out.println("逆序打印链表");
        reversePrint(singleLinkedList.getHead()); //逆序打印链表 没有改变链表本身结构
    }

    //方法：获取到单链表的节点的个数（如果是带头节点的链表，不统计头节点）

    /**
     * @param head 链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) { //链表为空
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;  //不统计头节点
        while (cur != null) {
            length++;
            cur = cur.next; //遍历
        }
        return length;
    }


    //合并两个单链表，有顺序加入<待完成>

    //使用栈，实现逆序打印单链表
    public static void reversePrint(HeroNode head){
        //链表为空
        if (head.next ==null){
            return;
        }
        //创建一个栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur =head.next;
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0){
            System.out.println(stack.pop()); //pop就是将栈顶是数据取出
        }
    }

    //将单链表进行反转 [腾讯面试]
    public static void reversetList(HeroNode head) {
        //链表为空
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义一个辅助的指针，帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null; //指向当前节点cur的下一节点
        HeroNode reverserHead = new HeroNode(0, "", "");
        //遍历原先的链表，取出，并放在新的链表reverseHead的最前端
        while (cur != null) {
            next = cur.next; //先暂时保存当前节点的下一个节点，后面需要使用
            cur.next = reverserHead.next; //将cur 的下一个节点指向新的链表的最前端
            reverserHead.next = cur; //将cur 连接到新的链表上
            cur = next;  //cur 后移
        }
        //将head.next 指向reverseHead.next,实现链表的反转
        head.next = reverserHead.next;
    }


    //查找单链表中的倒数第K个节点【新浪面试】
    //思路
    //1.编写一个方法，接收head节点，同时接收一个index
    //2.index 表示是倒数第index个节点
    //3.先把链表从头到尾遍历，得到链表的总的长度
    //4.得到size后，我们从链表的第一个开始遍历（size-index）个，就可以得到
    //5.如果找到了，就返回该节点，否则返回null
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //判断链表是否为空
        if (head.next == null) {
            return null;
        }
        //第一次遍历得到链表的长度（节点个数）
        int size = getLength(head);
        //第二次遍历 size - index 的位置 即为倒数第k个节点
        //先做一个校验
        if (index <= 0 || index > size) {
            return null;
        }
        //定义辅助变量
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }


}

class SingleLinkedList {
    //初始化头节点
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //当前不考虑顺序
    //1.找到最后的节点
    //2.将最后这个节点的next指向最后
    public void add(HeroNode heroNode) {

        //因为head不能动，所以需要一个辅助变量
        HeroNode temp = head;
        //遍历链表找到最后
        while (true) {
            //最后
            if (temp.next == null) {
                break;
            }
            //没找到 temp后移
            temp = temp.next;

        }
        //退出while循环时，temp指向链表最后
        temp.next = heroNode;
    }

    //按顺序添加
    public void addByOrder(HeroNode heroNode) {
        //头指针不能移动，需要一个辅助变量来帮助找到位置
        //单向链表，找到的temp要位于插入节点之前
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {//已经到链表的最后
                break;
            } else if (temp.next.no > heroNode.no) { //位置找到
                break;
            } else if (temp.next.no == heroNode.no) { //已经存在
                flag = true;
                break;
            }
            temp = temp.next; //后移
        }
        //判断flag的值
        if (flag) {
            System.out.printf("加入的编号已经存在%d,不能重复添加\n", heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
            //需要注意顺序
        }
    }

    //修改节点的信息，根据no编号修改，no不能改
    public void update(HeroNode newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到一个需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = true;
        while (true) {
            if (temp == null) {  //链表遍历完成
                break;
            }
            if (temp.no == newHeroNode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("没有找到需要修改的节点%d\n", newHeroNode.no);
        }
    }

    //删除节点
    public void delete(int no) {
        //链表非空判断
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head;  //不能写成   HeroNode temp = head.next;
        // 容易在temp.next.next 报空指针异常！！
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) { //找到了 返回
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;  //删除
        } else {
            System.out.printf("删除节点%d失败\n", no);
        }
    }


    //显示链表 --》 遍历
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，需要一个辅助变量
        HeroNode temp = head.next;
        while (true) {
            //判断是否到了链表的最后
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;  //后继

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }


    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
