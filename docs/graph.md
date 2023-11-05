核心思路
```java
/* 图节点的逻辑结构 */
class Graph {
    int id;
    Graph[] neighbors;
}
class Solution {
    // 记录被遍历过的节点
    boolean[] visited;
    // 记录从起点到当前节点的路径
    boolean[] onPath;

    /* 图遍历框架 */
    void traverse(Graph graph, int s) {
        if (visited[s]) return;
        // 经过节点 s，标记为已遍历
        visited[s] = true;
        // 做选择：标记节点 s 在路径上
        onPath[s] = true;
        for (Graph neighbor : graph.neighbors) {
            traverse(graph, neighbor.id);
        }
        // 撤销选择：节点 s 离开路径
        onPath[s] = false;
    }
}
```
* 名人问题 （n * n 二维数组，1表示认识，0表示不认识）
* 有向图的环检测 (循环依赖)
  监测对应的有没有重复
```java
class Solution {
    List<Integer>[] graph = new LinkedList[numCourses];
    boolean hasCycle = false;
    boolean[] onPath = new boolean[numCourses];
    boolean[] visited = new boolean[numCourses];
    for (int i = 0; i < numCourses; i++) {
        traverse(graph, i);
    }

    void traverse(List<Integer>[] graph, int s) {
        if (onPath[s]) {
            // 发现环！！！
            hasCycle = true;
        }
        if (visited[s] || hasCycle) {
            return;
        }
        // 将节点 s 标记为已遍历
        visited[s] = true;
        // 开始遍历节点 s
        onPath[s] = true;
        for (int t : graph[s]) {
            traverse(graph, t);
        }
        // 节点 s 遍历完成
        onPath[s] = false;
    }
}
```
* 拓扑排序算法
遍历后再来个后续操作
二叉树的后序遍历是什么时候？遍历完左右子树之后才会执行后序遍历位置的代码。
换句话说，当左右子树的节点都被装到结果列表里面了，根节点才会被装进去。
后序遍历的这一特点很重要，之所以拓扑排序的基础是后序遍历，是因为一个任务必须等到它依赖的所有任务都完成之后才能开始开始执行。
```java
class Solution {
    // 记录后序遍历结果
    List<Integer> postorder = new ArrayList<>();
    
    List<Integer>[] graph = new LinkedList[numCourses];
    boolean[] visited = new boolean[numCourses];
    for (int i = 0; i < numCourses; i++) {
        traverse(graph, i);
    }
    return postorder;

    void traverse(List<Integer>[] graph, int s) {
        if (visited[s]) {
            return;
        }
        // 将节点 s 标记为已遍历
        visited[s] = true;
        for (int t : graph[s]) {
            traverse(graph, t);
        }
        postorder.add(s);
    }
}
```

BFS算法

