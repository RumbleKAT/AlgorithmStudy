import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("/Users/songmyeongjin/eclipse-workspace/Solution/src/sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String [] str = br.readLine().split("-");

		//최소값을 만들기 위해선 최대한 큰수를 뺴준다, 덧셈으로 이루어진부분을 먼저 계산한다.
		/*
		* 1. 뺄셈을 기준으로 1차적으로 문자열을 분리한다.
		* 2. 분리된 문자열들을 각각에 포함된 정수 값들을 모두 더해준다.
		* 3. 각각 더해진 값들을 뺄셈해준다.
		* */
		ArrayList<Integer> subArr = new ArrayList<>();
		for(String temp : str){
			String [] plus = temp.split("\\+");
			int tempSum = 0;
			for(String temp2 : plus){
				tempSum += Integer.parseInt(temp2);
			}
			subArr.add(tempSum);
		}
		int ans = 0;
		if(subArr.size() >= 1){
			ans = subArr.get(0);
		}
		for(int i=1;i<subArr.size();i++){
			ans -= subArr.get(i);
		}
		System.out.println(ans);
	}

}

