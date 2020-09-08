package linkedlist;


//双向链表
public class DoubleSingleLinkedListDemo {
    public static void main(String[] args) {
        HeroDoubleNode heroDoubleNode1 =new HeroDoubleNode(1,"路飞","海贼王");
        HeroDoubleNode heroDoubleNode2 =new HeroDoubleNode(2,"索隆","三刀流");
        HeroDoubleNode heroDoubleNode3 =new HeroDoubleNode(3,"山治","黑足");
        HeroDoubleNode heroDoubleNode4 =new HeroDoubleNode(3,"娜美","航海士");

        DoubleSingleLinkedList doubleSingleLinkedList = new DoubleSingleLinkedList();
        doubleSingleLinkedList.add(heroDoubleNode1);
        doubleSingleLinkedList.add(heroDoubleNode2);
        doubleSingleLinkedList.add(heroDoubleNode3);
        doubleSingleLinkedList.list();
//        //删除3
////        doubleSingleLinkedList.delete(3);
////        System.out.println("删除3后的结果");
////        doubleSingleLinkedList.list();

        //修改
        System.out.println("修改3后的结果");
        doubleSingleLinkedList.update(heroDoubleNode4);
        doubleSingleLinkedList.list();


    }
}

class DoubleSingleLinkedList{
    //初始化头节点
    private HeroDoubleNode head = new HeroDoubleNode(0, "", "");

    public HeroDoubleNode getHead() {
        return head;
    }

    //添加
    public  void add(HeroDoubleNode heroDoubleNode){
        HeroDoubleNode temp =head;
        while (true){
            if (temp.next == null){  //到达链表的最后了
                break;
            }
            temp = temp.next;
        }
        temp.next=heroDoubleNode;
        heroDoubleNode.pre = temp;
    }

    //遍历
    public void list(){
        //判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroDoubleNode temp = head.next;
        while (true){
            if (temp == null){ //找到链表尾部了
                break;
            }
            System.out.println(temp);
            temp = temp.next;

        }
    }

    //删除
    public void delete(int no){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroDoubleNode temp =head.next;
        boolean flag = false;
        while (true){
            if (temp == null){
                System.out.println("链表为空");
                break;
            }
            if (temp.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.pre.next = temp.next;
            //代码有问题  可能是删除最后一个节点  temp.next.pre = temp.pre; 空指针异常
            if (temp.next != null){
                temp.next.pre = temp.pre;
            }

        }else {
            System.out.printf("删除节点%d失败\n", no);
        }
    }

    //修改节点的信息，根据no编号修改，no不能改
    public void update(HeroDoubleNode newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到一个需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroDoubleNode temp = head.next;
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

}

class HeroDoubleNode{
    public int no;
    public String name;
    public String nickname;
    public HeroDoubleNode pre;
    public HeroDoubleNode next;

    public HeroDoubleNode(int no,String name,String nickname){
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroDoubleNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}