import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N, M, K; //수의 개수, 변경 횟수, 구간합을 구하는 횟수
	private static long[] A; //수들을 저장하고 있는 배열
	
	//L[i]: 어떤 수 i를 이진수로 나타내었을 때, 마지막 1이 나타내는 값
	//예 : L[3] = 1, L[10] = 2, L[12] = 4
	//tree[i] 는 A[i]부터 앞으로(좌측으로) L[i][개의 합이 저장되어 있음
	
	private static long[] tree;
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		A = new long[N+1]; //1번 인덱스부터 사용하기 위해 +1
		tree = new long[N+1];
		
		//1. A 배열에 있는 원소들을 아 Fenwick Tree에 담기
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			A[i] = Long.parseLong(st.nextToken());
			update(i, A[i] - 0);
		}
		
		//2. Fenwick Tree를 이용하여 값 갱신 및 구간 합 구하기
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken());
			
			//업데이트 연산일 경우
			if (a == 1) {
				int b = Integer.parseInt(st.nextToken());
				long c = Long.parseLong(st.nextToken());
				update(b, c-A[b]); //바뀐 크기(diff) 만큼 적용
				A[b] = c; //배열 값 갱신
			}
			//구간 합(interval sum) 연산의 경우
			else if (a == 2) {
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				long sum = intervalSum(b, c);
				sb.append(sum).append("\n");
			}
		}
		
		System.out.println(sb);
	}

	//i: Fenwick Tree의 i번째 수
	//diff : 이전 값과 변경될 값의 차이(새로 들어온 입력. 구간합.)
	//i번째 수를 diff만큼 더하는 함수
	private static void update(int i, long diff) {
		while(i <= N) {
			tree[i] += diff;
			
			//i를 이진수로 나타냈을 때, 마지막 1이 나타내는 값 구하기
			//구한 값 만큼 점프
			i += (i & -i);
		}
	}
	
	//1부터 N까지의 합(누적합) 구하기
	private static long prefixSum(int i) {
		long result = 0;
		while(i > 0) {
			result += tree[i];
			
			//구한 값 만큼 앞으로(좌측으로) 점프
			i -= (i & -i);
		}
		return result;
	}
	
	private static long intervalSum(int start, int end) {
		return prefixSum(end) - prefixSum(start-1);
	}
}