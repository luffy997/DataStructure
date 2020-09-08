package reccursion;

public class MiGong {
    public static void main(String[] args) {

        //创建数组
        int[][] map=new int[8][7];
        //设置墙
        map[3][1] = 1;
        map[3][2] = 1;
//        map[1][2] = 1;
//        map[2][2] = 1;

        //上下两堵墙
        for (int i = 0; i < 7; i++) {
            map[0][i] =1;
            map[7][i] =1;
        }
        //左右两堵墙
        for (int j = 0; j < 7; j++) {
            map[j][0] =1;
            map[j][6] =1;
        }
        //输出map
        System.out.println("初始化地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("进行递归回溯给小球找路后的问题");
        setWay2(map,1,1);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    //使用递归回溯来给小球找路
    //说明
    //1. map 表示地图
    //2. i,j 表示从地图的哪个位置开始出发 (1,1)
    //3. 如果小球能到 map[6][5] 位置，则说明通路找到.
    //4. 约定： 当map[i][j] 为 0 表示该点没有走过 当为 1 表示墙  ； 2 表示通路可以走 ； 3 表示该点已经走过，但是走不通
    //5. 在走迷宫时，需要确定一个策略(方法) 下->右->上->左 , 如果该点走不通，再回溯
    /**
     *
     * @param map 表示地图
     * @param i 从哪个位置开始找
     * @param j
     * @return 如果找到通路，就返回true, 否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2){ //通路已经找到了
            return true;
        }else {
            if (map [i][j] == 0){ //表示该点没有走过
                //设置记号
                map[i][j] =2;
                //走的策略 下->右->上->左
                if (setWay(map,i+1,j)){
                    return true;
                }else if (setWay(map,i,j+1)){
                    return true;
                }else if (setWay(map,i-1,j)){
                    return true;
                }else if (setWay(map,i,j-1)){
                    return true;
                }else {  //下->右->上->左  都走过了 走不通 就把此路标记为3
                    map[i][j] =3;
                    return false;

                }
            }else {//已经走过了 有 1 2 3 三种情况
                return false;
            }

        }
    }

    //修改找路的策略
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2){ //通路已经找到了
            return true;
        }else {
            if (map [i][j] == 0){ //表示该点没有走过
                //设置记号
                map[i][j] =2;
                //走的策略 上->右->下->左
                if (setWay2(map,i-1,j)){
                    return true;
                }else if (setWay2(map,i,j+1)){
                    return true;
                }else if (setWay2(map,i+1,j)){
                    return true;
                }else if (setWay2(map,i,j-1)){
                    return true;
                }else {  //下->右->上->左  都走过了 走不通 就把此路标记为3
                    map[i][j] =3;
                    return false;

                }
            }else {//已经走过了 有 1 2 3 三种情况
                return false;
            }

        }
    }
}
