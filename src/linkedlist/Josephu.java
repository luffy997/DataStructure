package linkedlist;


//约瑟夫环
public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(125);
        circleSingleLinkedList.showBoy();

        //测试小孩出圈
        circleSingleLinkedList.countBoy(1,20,125);
    }
}

//创建一个环形的单向链表
class CircleSingleLinkedList {
    //创建一个first节点，当前没有编号
    private Boy first = new Boy(-1);

    public void addBoy(int nums) {
        //nums的数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;
        //for创建环形链表
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.next = first;
                curBoy = first;
            } else {
                curBoy.next = boy;
                boy.next = first;
                curBoy = boy;
            }
        }
    }

    //遍历
    public void showBoy() {
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        //first 不能动
        Boy cur = first;
        while (true) {
            System.out.printf("小孩的编号%d\n", cur.no);
            if (cur.next == first) {
                break;
            }
            cur = cur.next;
        }
    }


    //根据用户的输入，计算小孩出圈的顺序

    /**
     *
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少小孩在圈里
     */
    public void countBoy(int startNo , int countNum ,int nums){
        //先对数据进行校验
        if (first == null || startNo < 1 || startNo >nums){
            System.out.println("参数输入有误");
            return;
        }
        //创建辅助指针，帮助小孩出圈
        Boy helper = first;
        //需求 创建一个辅助指针（变量）helper,事先应该指向环形链表的最后这个节点
        while (true){
            if (helper.next == first){
                break;
            }
            helper = helper.next;
        }
        //小孩报数前，先让first 和 helper 移动 -1次
        for (int j = 0; j < startNo-1; j++) {
            first = first.next;
            helper = helper.next;
        }
        //当小孩报数时，让first 和 helper 指针同时移动 m -1次，然后出圈
        //循环操作，直到圈中只要一个节点
        while (true){
            if (helper == first){ //圈中只有一人
                break;
            }
            //first 和 helper 指针同时移动 countNum -1次
            for (int i = 0; i < countNum - 1; i++) {
                first = first.next;
                helper = helper.next;
            }
            //这时 first 指向的节点，就是要出圈的节点
            System.out.printf("小孩%d出圈\n",first.no);
            //这时将小孩出圈
            first = first.next;
            helper.next = first;
        }
        System.out.printf("最后留在圈中的小孩编号%d\n",first.no);
    }

}


//创建一个Boy类，表示节点
class Boy {
    public int no; //编号
    public Boy next; //指向下一个节点，默认为空

    public Boy(int no) {
        this.no = no;
    }
}