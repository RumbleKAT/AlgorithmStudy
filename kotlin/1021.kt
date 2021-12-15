import java.util.*
import java.io.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine()!!.split(" ").map{it.toInt()}
    val choice = br.readLine()!!.split(" ").map{it.toInt()}.toIntArray()
    var q = LinkedList<Int>()
    for( i in 1..n) q.add(i)
    var res = 0
    var count = 0
    /*
        큐의 첫번째 위치에서 +1 / 큐의 끝점에서 -1
        반복해서 도착하고자 하는 번호에 먼저 도달하는 것이 비용이 작게 든다.
    */
    while(count != m){
        if(q.first == choice[count]){
            q.poll()
        }else{
            for(i in 1 until q.size){
                if(q[q.size-i] == choice[count]){
                    while(q[0]!= choice[count]){
                        q.add(0, q.last)
                        q.removeLast()
                        res++
                    }
                    q.poll()
                    break
                }else if(q[i] == choice[count]){
                    while(q[0] != choice[count]){
                        q.add(q.first)
                        q.removeFirst()
                        res++
                    }
                    q.poll()
                    break
                }
            }
        }
        count++
    }
    println(res)
}