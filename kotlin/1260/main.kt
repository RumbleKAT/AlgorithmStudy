import java.util.*
import java.io.*

var N:Int = 0
var M:Int = 0
var V:Int = 0
var graph: Array<IntArray> = arrayOf()
var visited: BooleanArray = booleanArrayOf()

//val path = "/Users/songmyeongjin/Desktop/1260/src/main/kotlin/sample.txt"
//BufferedReader(FileReader(path))

fun main(args: Array<String>) = with(
    BufferedReader(InputStreamReader(System.`in`))){

    val arr  = readLine().split(" ")
    N = arr[0].toInt()
    M = arr[1].toInt()
    V = arr[2].toInt()

    graph = Array(N+1){ IntArray(N+1) }
    visited = BooleanArray(N+1)

    for( i in 0 until M){
        val str = readLine().split(" ")
        val a = str[0].toInt()
        val b = str[1].toInt()

        graph[a][b] = 1
        graph[b][a] = 1
    }

    for(i in 0 until N+1)
        visited[i] = false

    dfs(V)
    println()

    for(i in 0 until N+1)
        visited[i] = false

    bfs(V)
}

fun bfs(v: Int) {
    val queue: Queue<Int> = LinkedList<Int>()
    queue.add(v)
    visited[v] = true
    print("$v ")

    while(!queue.isEmpty()){
        val cur = queue.poll()
        for(i in 0 until N+1){
            if(!visited[i]){
                if(graph[cur][i] == 1){
                    print("$i ")
                    visited[i] = true
                    queue.add(i)
                }
            }
        }
    }

}

fun dfs(v: Int) {
    if(visited[v]) return
    visited[v] = true
    print("$v ")

    for (i in 1 until N + 1) {
        if (!visited[i]) {
            if (graph[v][i] == 1) {
                dfs(i)
            }
        }
    }
}




