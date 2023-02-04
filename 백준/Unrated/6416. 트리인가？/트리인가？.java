import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	//입력받을 node를 저장할 map
	//key : node name
	//value : key node의 adjacent node list
	private static Map<Integer, List<Integer>> tree = new HashMap<>();
	private static Map<Integer, Integer> isRoot = new HashMap<>();
	private static boolean isEnd = false;
	private static boolean isTestcaseEnd = false;
	private static int root;
	private static boolean isTree = true;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		Scanner sc = new Scanner(System.in);
	
		int tc = 1;
		while(!isEnd) {
			
			//테케 하나 당 input
			while (!isTestcaseEnd && !isEnd){
				int node = sc.nextInt();
				int adj = sc.nextInt();
				addNode(node, adj);
			}
			if (isEnd) break;
			//트리의 조건
			//1. 루트 존재
			//2. 루트에서 다른 '모든' 노드로 가는 유일한 하나의 길 존재
			//3. cycle 없음
			if (!tree.isEmpty() && haveRoot()) {
				for (int k : isRoot.keySet()) {
					isRoot.replace(k, 0);
				}
				for (int adjNode : tree.get(root)) {
					dfs(adjNode);
				}
				
				if (!isConnectAll()) {
					isTree = false;
				}
			}
			
			//출력
			if (isTree) System.out.println("Case " + tc + " is a tree.");
			else System.out.println("Case " + tc + " is not a tree.");
			//초기화
			root = 0;
			tree.clear();
			isRoot.clear();
			isTestcaseEnd = false;
            isTree = true;
			tc++;
		}

	}
	
	private static void addNode(int inNode, int inAdjNode) {
		if (inNode == 0 && inAdjNode == 0) { //테케의 끝에는 '0 0' 입력
			isTestcaseEnd = true;
			return;
		}
		else if (inNode < 0 && inAdjNode < 0) { //테케의 끝에는 음수 입력
			isEnd = true;
			isTestcaseEnd = true; //이걸 해야 끝남
			return;
		}

		//key가 이미 존재: inKey에 해당하는 노드를 tree에서 가져와서 list에 inVal 추가
		if (tree.keySet().contains(inNode)) {
			tree.get(inNode).add(inAdjNode);
		}
		//key가 존재하지 않음 : inKey와 함께 List를 생성해서 tree에 넣어줌
		else {
			List<Integer> adjNodes = new ArrayList<>();
			adjNodes.add(inAdjNode);
			tree.put(inNode, adjNodes);
		}
	}
	
	private static boolean haveRoot() {
		//tree를 돌면서 노드 추가
		for (int node : tree.keySet()) {
			if (!isRoot.containsKey(node)) {
				isRoot.put(node, 0);
			}
			
			for (int adjNode : tree.get(node)) {
				if (isRoot.containsKey(adjNode)) {
					isRoot.replace(adjNode, 1);
				}
				else {
					isRoot.put(adjNode, 1);
				}
			}
		}
		
		//isRoot의 val 값이 0 인 노드가 root
		int rootCnt = 0;
		for (int node : isRoot.keySet()) {
			//System.out.println("node: " + node + " adj: " + isRoot.get(node));
			if (isRoot.get(node) == 0) {
				root = node;
				rootCnt++;
			}
		}
		if (rootCnt != 1) {
			isTree = false;
			return false;
		}
		else return true;
	}
	
	private static void dfs(int node) {
		if (!isTree) return;
		
		if (isRoot.get(node) != 0) { //이미 방문한 노드
			//tree 아님
			isTree = false;
			return;
		}
		isRoot.replace(node, 1);
		
		if (!tree.containsKey(node)) return;
		for (int adjNode : tree.get(node)) {
			dfs(adjNode);
		}
	}
	
	private static boolean isConnectAll() {
		for (int node : isRoot.keySet()) {
			if (node == root) continue;
			if (isRoot.get(node) == 0) return false;
		}
		return true;
	}
}