package bfs;

import java.util.Scanner;

public class BOJ_2606_양윤희 {

	static int[] visited = new int[101];
	static int[][] arr = new int[101][101];
	static int virus = 0;
	static int n;

	static void dfs(int xy) {
		for(int i=1; i<=n; i++) {
			if(arr[xy][i] == 1 && visited[i]==0) {
				visited[i]=1;
				virus++;
				dfs(i);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		int m = sc.nextInt();


		for (int i = 0; i < m; i++) {
			int y = sc.nextInt();
			int x = sc.nextInt();

			arr[y][x] = 1;
			arr[x][y] = 1;

		}
		visited[1] = 1;
		dfs(1);
		System.out.println(virus);
	}
}
