import java.util.*
import java.io.*
import kotlin.*

var N:Int = 0
var M:Int = 0
var array: IntArray = intArrayOf()

//val path = "/Users/songmyeongjin/Desktop/1260/src/main/kotlin/sample.txt"
//BufferedReader(FileReader(path))

fun main(args: Array<String>) = with(
    BufferedReader(InputStreamReader(System.`in`))){
    var token = StringTokenizer(readLine())
    N = token.nextToken().toInt()
    M = token.nextToken().toInt()
    array = IntArray(N)
    token = StringTokenizer(readLine())

    for(i in 0 until N){
        array[i] = token.nextToken().toInt()
    }
    var min:Int = Int.MAX_VALUE
    var ans:Int = 0
    for(i in 0 until N){
        for(j in i+1 until N){
            for(k in j+1 until N){
                val sum = array[i] + array[j] + array[k]
                if(sum <= M && sum > max ){
                    max = sum
                }
            }
        }
    }
    println(ans)
}


