package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class GraphTest {

    private ArrayList<String> vertexList; //顶点集合
    private int[] []  edges; //存储边的二维数组
    private int numOfEdges; //表示边的数目
    //定义给数组boolean[], 记录某个结点是否被访问
    private boolean[] isVisited;

    public static void main(String[] args) {
        int n = 6;
        String vertexs [] ={"A","B","C","D","E"};

        GraphTest graphTest = new GraphTest(n);

        for (String vertex : vertexs){
            graphTest.insertVertex(vertex);
        }

		graphTest.insertEdge(0, 1, 1);
		graphTest.insertEdge(0, 2, 1);
		graphTest.insertEdge(1, 2, 1);
		graphTest.insertEdge(1, 3, 1);
		graphTest.insertEdge(1, 4, 1);
        graphTest.showGraph();

        //深度优先遍历
        System.out.println("图的深度优先遍历");
        graphTest.dfs();
        System.out.println();
        System.out.println("图的广度优先遍历");
        graphTest.bfs();

    }

    //获取当前节点的邻接节点
    /**
     *
     * @param index 当前节点的下标
     * @return 有就返回下标 没有就返回-1
     */
    private int getFirstNeighbor(int index){
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0){
                return i;
            }
        }
        return -1;
    }

    //根据前一个邻接结点的下标来获取下一个邻接结点

    /**
     *
     * @param v1
     * @param v2
     * @return
     */
    private int getNextNeighbor(int v1, int v2){
        for (int i = v2 +1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0){
                return i;
            }
        }
        return -1;
    }

    //深度优先遍历

    /**
     *
     * @param isVisited 存储是否遍历过的数组
     * @param index 当前节点索引 一个
     */
    public void dfs(boolean [] isVisited,int index){
        //首先输出当前节点
        System.out.print(vertexList.get(index)+"=>");
        isVisited [index] = true;
        //查找当前节点的邻接节点
        int w = getFirstNeighbor(index);
        while (w != -1){
            if (!isVisited[index]){
                dfs(isVisited,w); //递归调用
            }
             w = getNextNeighbor(index,w);
        }
    }

    //对dfs进行重写
    public void dfs(){
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }


    //广度优先遍历
    private void bfs(boolean[] isVisited, int index){
        int u ; // 表示队列的头结点对应下标
        int w ; // 邻接结点w
        //队列，记录结点访问的顺序
        LinkedList queue = new LinkedList();
        //访问结点，输出结点信息
        System.out.print(getValueByIndex(index) + "=>");
        //标记为已访问
        isVisited[index] = true;
        //将结点加入队列
        queue.addLast(index);

        while (!queue.isEmpty()){
            u = (int) queue.removeFirst();
            w = getFirstNeighbor(u);
            while (w != -1){  //找到
                if (!isVisited[w]){
                    System.out.print(getValueByIndex(w)+"=>");
                    isVisited[w] =true;
                    queue.addLast(w);
                }
                //以u为前驱点，找w后面的下一个邻结点
                w = getNextNeighbor(u, w); //体现出我们的广度优先
            }

        }
    }

    //遍历所有的结点，都进行广度优先搜索
    public void bfs() {
        isVisited = new boolean[vertexList.size()];
        for(int i = 0; i < getNumVertext(); i++) {
            if(!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }


    public GraphTest(int n) {
        //初始化矩阵
        vertexList = new ArrayList<String>(n);
        edges = new int[n][n];
        numOfEdges = 0;
    }

    //常用方法
    //返回节点个数
    public int getNumVertext(){
        return vertexList.size();
    }

    //显示图对应的矩阵
    public void showGraph(){
        for (int [] graph: edges ) {
            System.out.println(Arrays.toString(graph));
        }
    }

    //返回结点i(下标)对应的数据 0->"A" 1->"B" 2->"C"
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    public void insertVertex(String value){
        vertexList.add(value);
    }

    public void insertEdge(int v1,int v2,int weight){
        edges [v1][v2] = weight;
        edges [v2][v1] = weight;
        numOfEdges++;
    }
}
