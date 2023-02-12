import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.swing.plaf.TreeUI;

public class Main {

	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int[] population; //1~N
	static Map<Integer, List<Integer>> info = new HashMap<>();
	static boolean[] visited; //1~N
	static int minDiff = Integer.MAX_VALUE;
	static boolean isDivide = false;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//input
		N = Integer.parseInt(in.readLine());
		
		//인구수
		population = new int[N+1];
		String[] line = in.readLine().split(" ");
		for (int i = 0; i < line.length; i++) {
			population[i+1] = Integer.parseInt(line[i]);
		}
		
		//info
		for (int i = 1; i <= N; i++) {
			line = in.readLine().split(" ");
			List<Integer> adjs = new ArrayList<>();
			for (int j = 1; j < line.length; j++) {
				adjs.add(Integer.parseInt(line[j]));
			}
			info.put(i, adjs);
		}
		
		
		//logic
		visited = new boolean[N+1];
		divide(0, 1);
		if (isDivide) System.out.println(minDiff);
		else System.out.println("-1");
	}

	private static void divide(int cnt, int start) { //조합...
		if (cnt == N) return;
		
		if (cnt >= 1) { //공집합 아니면
//			모든 조합이 다 찍히는지 찍어봄.
//			for (int i = 1; i < visited.length; i++) {
//				System.out.print(visited[i] + " ");
//			}
//			System.out.println();
			if (isConnected(visited.clone())) {
//				System.out.println("-------------------------");
				isDivide = true;
				int diff = getDiff(visited.clone());
//				System.out.println(diff);
				if (minDiff > diff) minDiff = diff;
			}
			trueFlag = false;
			falseFlag = false; //초기화1!!!! -- 아ㅏ 위치.....
			myTcnt = 1;
			myFcnt = 1;
		}
		
		
		for (int i = start; i <= N; i++) {
			if (visited[i]) continue;
			
			visited[i] = true;
			divide(cnt + 1, i + 1);
			visited[i] = false;
		}
	}

	private static int getDiff(boolean[] clone) {
		int trueSum = 0;
		int falseSum = 0;
		for (int i = 1; i <= N; i++) {
			if (clone[i]) trueSum += population[i];
			else falseSum += population[i];
		}
		return Math.abs(trueSum - falseSum);
	}
	
	static boolean trueFlag = false;
	static int trueCnt = 0;
	static boolean falseFlag = false;
	static int falseCnt = 0;
	private static boolean isConnected(boolean[] visitClone) {
		
		//true인 것들
		for (int i = 1; i <= N; i++) {
			if (visitClone[i]) {
				trueCnt = getTrueCnt();
//				System.out.println("true : " + trueCnt);
				boolean[] trueClone = visitClone.clone();
				trueClone[i] = false;
				trueBfs(i, trueClone);
				break;
			}
		}
		
		//false인 것들
		for (int i = 1; i <= N; i++) {
			if (!visitClone[i]) {
				falseCnt = N - trueCnt;
//				System.out.println("false : " + falseCnt);
				boolean[] falseClone = visitClone.clone();
				falseClone[i] = true;
				falseBfs(i, falseClone);
				break;
			}
		}
		
//		System.out.println("trueFlag: " + trueFlag + " falseFlag : " + falseFlag);
		return trueFlag && falseFlag;
	}
	
	static int myFcnt = 1;
	private static void falseBfs(int start, boolean[] visitClone) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		
		while(!q.isEmpty()) {
//			System.out.println("false");
			int node = q.poll();
			
			for (int adj : info.get(node)) {
				if (visitClone[adj]) continue;
				
				visitClone[adj] = true;
				q.offer(adj);
				myFcnt++;
			}
		}
		if (myFcnt == falseCnt) falseFlag = true;
	}
	static int myTcnt = 1;
	private static void trueBfs(int start, boolean[] visitClone) {

		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		
		while(!q.isEmpty()) {
//			System.out.println("true");
			int node = q.poll();
			
			for (int adj : info.get(node)) {
				if (!visitClone[adj]) continue;
				
				visitClone[adj] = false;
				q.offer(adj);
				myTcnt++;
			}
		}
		if (myTcnt == trueCnt) trueFlag = true;
	}

	private static int getTrueCnt() {
		int trueCnt = 0;
		for (int i = 1; i <= N; i++) {
			if (visited[i]) trueCnt++;
		}
		return trueCnt;
	}
	
}
